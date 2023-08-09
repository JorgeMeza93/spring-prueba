package comdev4j.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import comdev4j.users.model.User;
import comdev4j.users.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(@RequestParam(required = false, value = "startsWith") String startsWith){
		return new ResponseEntity<List<User>>(userService.getUsers(startsWith), HttpStatus.OK);	
	}
	@GetMapping(value="/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
		return new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
	@PutMapping("/{username}")
	public ResponseEntity<User> updateUser(@PathVariable("username") String username, @RequestBody User user){
		return new ResponseEntity<User>(userService.updateUser(user, username), HttpStatus.OK);
	}
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username")String username){
		userService.deleteUser(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
