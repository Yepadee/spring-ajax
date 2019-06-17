package uk.james_hawkins.repository.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import uk.james_hawkins.model.user.User;


public interface BaseUserRepository<T extends User> extends CrudRepository<T, Long> {
	Optional<T> findByUserName(String userName);
}
