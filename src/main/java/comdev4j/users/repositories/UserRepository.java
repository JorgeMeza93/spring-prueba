package comdev4j.users.repositories;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import comdev4j.users.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	/* NO ES SQL, ES JPQL */
	@Query("SELECT u.username FROM User u")
	public Page<String> findUsernames(Pageable pageable);
	public Optional<User> findByUsernameAndPassword(String username, String password);
	public Optional<User> findByPassword(String password);
	public Optional<User> findByUsername(String username);
}
