package comdev4j.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;

import comdev4j.users.entities.User;
import comdev4j.users.repositories.UserRepository;

@SpringBootApplication
public class UsersAppApplication implements ApplicationRunner{
	
	@Autowired
	private Faker faker;
	@Autowired
	private UserRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(UsersAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for(int i = 0;i<19;i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			repository.save(user);
		}
		
	}

}
