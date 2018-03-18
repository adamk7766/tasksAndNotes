package com.example.tasksAndNotes.repository;

import com.example.tasksAndNotes.model.Note;
import com.example.tasksAndNotes.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task,Long> {
}
