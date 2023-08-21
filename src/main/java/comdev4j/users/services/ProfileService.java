package comdev4j.users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import comdev4j.users.entities.Profile;
import comdev4j.users.entities.User;
import comdev4j.users.repositories.ProfileRepository;
import comdev4j.users.repositories.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private UserRepository userRepository;
	
	public Profile create(Integer id, Profile profile) {
		Optional<User> result = userRepository.findById(id);
		if(result.isPresent()){
			profile.setUser(result.get());
			return profileRepository.save(profile);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Usuario %d no encontrado", id));
		}
	}
	
	public Profile getByUserIdAndProfileId(Integer userId, Integer profileId) {
		return profileRepository.findByUserIdAndProfileId(userId, profileId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Usuario con usuario %d y/o perfil %d no encontrado", userId, profileId)));
	}
}
