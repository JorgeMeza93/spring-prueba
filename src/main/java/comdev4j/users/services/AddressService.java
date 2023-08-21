package comdev4j.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import comdev4j.users.entities.Address;
import comdev4j.users.entities.Profile;
import comdev4j.users.repositories.AddressRepository;
import comdev4j.users.repositories.ProfileRepository;


@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ProfileRepository profileRepository;

	public List<Address> findAddressesByProfileAndUserId(Integer userId, Integer profileId) {
		return addressRepository.findByProfileId(userId, profileId);
	}
	public Address createAddress(Integer userId, Integer profileId, Address address) {
		Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);
		if(result.isPresent()) {
			address.setProfile(result.get());
			return addressRepository.save(address);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Usuario con id %d y perfil id %d no encontrado", userId, profileId));
		}
	} 
}
