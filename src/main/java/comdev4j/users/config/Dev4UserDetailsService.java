package comdev4j.users.config;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import comdev4j.users.entities.User;
import comdev4j.users.entities.UserInRole;
import comdev4j.users.repositories.UserInRoleRepository;
import comdev4j.users.repositories.UserRepository;

public class Dev4UserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(Dev4UserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = userRepository.findByUsername(username);
		if(optional.isPresent()) {	
			User user = optional.get();	
			List<UserInRole> userInRoles = userInRoleRepository.findByUser(user);
			logger.info("El resultado es " + userInRoles.size());
			String[] roles = userInRoles.stream().map((r) -> r.getRole().getName()).toArray(String[]::new);
			return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
					.password(passwordEncoder.encode(user.getPassword())).roles(roles).build();
		}
		else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
	}


	

}
