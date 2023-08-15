package comdev4j.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comdev4j.users.entities.User;
import comdev4j.users.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired  
	private UsersService service;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(service.getUsers(), HttpStatus.OK);
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
		return new ResponseEntity<User>(service.getUserByUsernameAndPassword(user.getUsername(), user.getUsername()), HttpStatus.OK);
	}
	
	
}
