package lee.andyfxq.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import lee.andyfxq.model.ERole;
import lee.andyfxq.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	
	Optional<Role> findByName(ERole name);

}
