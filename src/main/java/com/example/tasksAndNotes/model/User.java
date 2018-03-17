package com.example.tasksAndNotes.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();

    public List<Note> getBooks() {
        return notes;
    }

    public void setBooks(List<Note> books) {
        this.notes = books;
    }

    @PreRemove
    private void preRemove() {
        System.out.println("Usunięto autora");
    }

    public int getNumberOfBooks(){
        return notes.size();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return getName()+" "+getSurname();
    }
}
