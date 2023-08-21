package comdev4j.users.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comdev4j.users.entities.Address;
import comdev4j.users.services.AddressService;


@RestController
@RequestMapping("/users/{userId}/profile/{profileId}/addresses")
public class AddressController {
	
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<Address>> findAddressesByProfileId(@PathVariable("userId") Integer userId, @PathVariable("profileId") Integer profileId){
		return new ResponseEntity<List<Address>>(addressService.findAddressesByProfileAndUserId(userId, profileId), HttpStatus.OK);
	}
}
