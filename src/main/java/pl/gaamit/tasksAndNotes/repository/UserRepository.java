package pl.gaamit.tasksAndNotes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.gaamit.tasksAndNotes.model.User;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
