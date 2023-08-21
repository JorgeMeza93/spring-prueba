package comdev4j.users.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import comdev4j.users.entities.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>{
	
	@Query("SELECT a from Address a WHERE a.profile.user.id=?1 AND a.profile.id=?2")
	public List<Address> findByProfileId(Integer userId, Integer profileId);
}
