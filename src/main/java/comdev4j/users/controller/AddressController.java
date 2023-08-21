package comdev4j.users.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comdev4j.users.entities.Address;
import comdev4j.users.services.AddressService;


@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/addresses")
public class AddressController {
	public Logger logger = LoggerFactory.getLogger(AddressController.class);
	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<Address>> findAddressesByProfileId(@PathVariable("userId") Integer userId, @PathVariable("profileId") Integer profileId){
		logger.info("Visitando direcciones");
		return new ResponseEntity<List<Address>>(addressService.findAddressesByProfileAndUserId(userId, profileId), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Address> create(@PathVariable("userId") Integer userId, @PathVariable("profileId") Integer profileId, @RequestBody Address address){
		return new ResponseEntity<Address>(addressService.createAddress(userId, profileId, address), HttpStatus.CREATED);
	}
	
}
