package comdev4j.users.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import comdev4j.users.entities.User;
import comdev4j.users.repositories.UserRepository;

@Service
public class UsersService {
	private static final Logger logger = LoggerFactory.getLogger(UsersService.class);
	@Autowired
	private UserRepository userRepository;
	
	public Page<User> getUsers(int page, int size){
		//return userRepository.findAll();
		return userRepository.findAll(PageRequest.of(page, size));
	}
	public	List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public Page<String> getUsernames(int page, int size){
		return userRepository.findUsernames(PageRequest.of(page, size));
	}
	
	public User getUserById(Integer userId){
		return userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Usuario con id %d no encontrado", userId)));
	}
	@Cacheable("users")
	public User getUserByUsername(String username) {
		logger.info("Getting user by username {}", username);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return userRepository.findByUsername(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Usuario con username %s no encontrado", username)));
	}
	@CacheEvict("users")
	public void deleteByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Usuario %s no encontrado", username)));
		userRepository.delete(user);
	}
	public User getUserByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Usuario %s no encontrado.", password)));
	}
	public User getUserByPassword(String password) {
		return userRepository.findByPassword(password).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Password %s not found", password)));
	}
	
	
}
