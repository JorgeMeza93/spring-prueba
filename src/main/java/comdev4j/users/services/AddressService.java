package comdev4j.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comdev4j.users.entities.Address;
import comdev4j.users.repositories.AddressRepository;


@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	
	public List<Address> findAddressesByProfileAndUserId(Integer userId, Integer profileId) {
		return addressRepository.findByProfileId(userId, profileId);
	}
	
}
