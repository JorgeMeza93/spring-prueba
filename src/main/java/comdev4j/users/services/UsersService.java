package comdev4j.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import comdev4j.users.entities.User;
import comdev4j.users.repositories.UserRepository;

@Service
public class UsersService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public User getUserById(Integer userId){
		return userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Usuario con id %d no encontrado", userId)));
	}
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Usuario con username %s no encontrado", username)));
	}
	public User getUserByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Usuario %s no encontrado", username)));
	}
	
	
	
}
