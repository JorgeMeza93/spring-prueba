package comdev4j.users.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import comdev4j.users.entities.User;
import comdev4j.users.entities.UserInRole;
import comdev4j.users.repositories.UserInRoleRepository;
import comdev4j.users.repositories.UserRepository;

@Service
public class Dev4UserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = userRepository.findByUsername(username);
		if(optional.isPresent()) {
			User user = optional.get();
			List<User> userInRoles = userInRoleRepository.findUsersByRoleName(username);
		
		}
		else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		return null;
	}
	
	

}
