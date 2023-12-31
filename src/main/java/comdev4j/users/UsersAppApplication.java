package comdev4j.users;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;

import comdev4j.users.entities.Role;
import comdev4j.users.entities.User;
import comdev4j.users.entities.UserInRole;
import comdev4j.users.repositories.RoleRepository;
import comdev4j.users.repositories.UserInRoleRepository;
import comdev4j.users.repositories.UserRepository;

@SpringBootApplication
public class UsersAppApplication implements ApplicationRunner{
	
	@Autowired
	private Faker faker;
	@Autowired
	private UserRepository repository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	private static final Logger log = LoggerFactory.getLogger(UsersAppApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(UsersAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role[] roles = {new Role("ADMIN"), new Role("USER"), new Role("SUPPORT")};
		for(Role role: roles) {
			roleRepository.save(role);
		} 
		for(int i = 0;i<19;i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			User userCreated = repository.save(user);
			UserInRole userInRole = new UserInRole(userCreated, roles[new Random().nextInt(3)]);
			/*log.info("User created username {} password {} role {}", userCreated.getUsername(), userCreated.getPassword(), userInRole.getRole().getName());
			userInRoleRepository.save(userInRole); */
		} 
		
	}

}
