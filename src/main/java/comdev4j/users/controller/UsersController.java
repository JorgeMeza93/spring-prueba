package comdev4j.users.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import comdev4j.users.entities.User;
import comdev4j.users.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired  
	private UsersService service;
	
	@GetMapping
	public ResponseEntity<Page<User>> getUsers(@RequestParam(value ="page", required = false, defaultValue = "0") int page, 
			@RequestParam(value = "size", required = false, defaultValue = "5") int size){
		return new ResponseEntity<Page<User>>(service.getUsers(page, size), HttpStatus.OK);
	}
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId){
		return new ResponseEntity<User>(service.getUserById(userId), HttpStatus.OK);
	}
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
		return new ResponseEntity<User>(service.getUserByUsername(username), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<User> authenticate(@RequestBody User user){
		return new ResponseEntity<User>(service.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
	}
	@GetMapping("/password/{password}")
	public ResponseEntity<User> getUserByPassword(@PathVariable("password") String password){
		return new ResponseEntity<User>(service.getUserByPassword(password), HttpStatus.OK);
	}
	@GetMapping("/usernames")
	public ResponseEntity<Page<String>> getUsernames(@RequestParam(value="page", required=false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size){
		return new ResponseEntity<Page<String>>(service.getUsernames(page, size), HttpStatus.OK);
	}
	@DeleteMapping("/username/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
		service.deleteByUsername(username);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
