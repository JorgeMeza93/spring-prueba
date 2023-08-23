package comdev4j.users.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import comdev4j.users.entities.UserInRole;

@Repository
public interface UserInRoleRepository extends CrudRepository<UserInRole, Integer>{

}
