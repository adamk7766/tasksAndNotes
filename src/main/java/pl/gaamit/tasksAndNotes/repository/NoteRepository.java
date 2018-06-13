package pl.gaamit.tasksAndNotes.repository;

import pl.gaamit.tasksAndNotes.model.Note;
import org.springframework.data.repository.CrudRepository;


public interface NoteRepository extends CrudRepository<Note, Long> {
}
