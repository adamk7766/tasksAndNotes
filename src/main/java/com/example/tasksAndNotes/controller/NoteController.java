package com.example.tasksAndNotes.controller;

import com.example.tasksAndNotes.model.Note;
import com.example.tasksAndNotes.repository.NoteRepository;
import com.example.tasksAndNotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;


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

    @GetMapping("note/{id}/edit")
    public String edit(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("note", noteRepository.findById(id).get());
        modelMap.addAttribute("users",userRepository.findAll());
        return "notes/edit";
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="action=cancel")
    public String cancel() {
        return "notes/notesList";
    }


}
