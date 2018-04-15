package pl.gaamit.tasksAndNotes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.gaamit.tasksAndNotes.model.Role;


public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);

}
