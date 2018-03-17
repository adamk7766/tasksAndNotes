package com.example.tasksAndNotes.repository;

import com.example.tasksAndNotes.model.Note;
import org.springframework.data.repository.CrudRepository;


public interface NoteRepository extends CrudRepository<Note,Long> {
}
