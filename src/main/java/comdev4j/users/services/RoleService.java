package comdev4j.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import comdev4j.users.entities.Role;
import comdev4j.users.entities.User;
import comdev4j.users.model.AuditDetails;
import comdev4j.users.repositories.RoleRepository;
import comdev4j.users.repositories.UserInRoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;
	private ObjectMapper mapper = new ObjectMapper();
	
	public List<Role> getRoles(){
		Iterable<Role> roles = repository.findAll();
		List<Role> listRoles = new ArrayList<Role>();
		roles.forEach(listRoles::add);
		return listRoles;
	}
	public List<User> getUsersByRole(String roleName){
		return userInRoleRepository.findUsersByRoleName(roleName);
	}
	public Role createRole(Role role) {
		Role roleCreated = repository.save(role);
		SecurityContextHolder.getContext().getAuthentication().getName();
		AuditDetails details = new AuditDetails(SecurityContextHolder.getContext().getAuthentication().getName(), role.getName());
		try {
			kafkaTemplate.send("devs4j-topic", mapper.writeValueAsString(details));
		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error parsing the message");
		}
		return roleCreated;
	}
	public Role updateRole(Integer roleId, Role role) {
		Optional<Role> result = repository.findById(roleId);
		if(result.isPresent()) {
			return repository.save(role);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Id role %d no existe", roleId));
		}
	}
	public void deleteRole(Integer id) {
		Optional<Role> result = repository.findById(id);
		if(result.isPresent()) {
			repository.delete(result.get());
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Id role %d no existe", id));
		}
		
	}
}
