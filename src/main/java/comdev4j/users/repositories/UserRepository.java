package comdev4j.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import comdev4j.users.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
