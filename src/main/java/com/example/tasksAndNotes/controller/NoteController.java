package com.example.tasksAndNotes.controller;

import com.example.tasksAndNotes.model.Note;
import com.example.tasksAndNotes.repository.NoteRepository;
import com.example.tasksAndNotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    /*@GetMapping("/")
    public String Main(){
        return "notes/notesList";
    }*/



    @GetMapping("/notes/add")
    public String add(ModelMap modelMap){
        modelMap.addAttribute("note",new Note());
        modelMap.addAttribute("users",userRepository.findAll());
        return "notes/add";
    }

    @PostMapping("/notes")
    public String save(@ModelAttribute Note note){
        noteRepository.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/notes")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("notes",noteRepository.findAll());
        return "notes/notesList";
    }

    @GetMapping("note/{id}")
    public String show(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("note", noteRepository.findById(id).get());
        return "notes/show";
    }

    @GetMapping("note/{id}/delete")
    public String delete(@PathVariable Long id,ModelMap modelMap){
        noteRepository.deleteById(id);
        return "redirect:/notes";
    }



}
