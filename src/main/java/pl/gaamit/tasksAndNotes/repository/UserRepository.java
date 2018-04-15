package pl.gaamit.tasksAndNotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gaamit.tasksAndNotes.model.User;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
