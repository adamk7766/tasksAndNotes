package com.example.tasksAndNotes.repository;

import com.example.tasksAndNotes.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long> {
}
