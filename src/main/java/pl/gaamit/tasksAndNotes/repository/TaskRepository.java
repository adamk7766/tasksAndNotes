package pl.gaamit.tasksAndNotes.repository;

import pl.gaamit.tasksAndNotes.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task,Long> {
}
