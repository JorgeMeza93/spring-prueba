package comdev4j.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import comdev4j.users.entities.Role;
import comdev4j.users.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	public List<Role> getRoles(){
		Iterable<Role> roles = repository.findAll();
		List<Role> listRoles = new ArrayList<Role>();
		roles.forEach(listRoles::add);
		return listRoles;
	}
	public Role createRole(Role role) {
		return repository.save(role);
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
