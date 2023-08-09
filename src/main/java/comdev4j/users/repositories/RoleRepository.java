package comdev4j.users.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import comdev4j.users.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
