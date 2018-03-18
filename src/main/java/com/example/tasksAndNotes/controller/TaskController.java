package com.example.tasksAndNotes.controller;


import com.example.tasksAndNotes.model.Task;
import com.example.tasksAndNotes.repository.NoteRepository;
import com.example.tasksAndNotes.repository.TaskRepository;
import com.example.tasksAndNotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    /*@GetMapping("/")
    public String Main(){
        return "notes/notesList";
    }*/


    @GetMapping("/tasks/add")
    public String add(ModelMap modelMap){
        modelMap.addAttribute("task",new Task());
        modelMap.addAttribute("users",userRepository.findAll());
        return "tasks/add";
    }

    @PostMapping("/tasks")
    public String save(@ModelAttribute Task task){
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("tasks",taskRepository.findAll());
        return "tasks/tasksList";
    }

    @GetMapping("task/{id}")
    public String show(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("task", taskRepository.findById(id).get());
        return "tasks/show";
    }

    @GetMapping("task/{id}/delete")
    public String delete(@PathVariable Long id,ModelMap modelMap){
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping("task/{id}/edit")
    public String edit(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("task", taskRepository.findById(id).get());
        return "tasks/edit";
    }

    @RequestMapping(value="/edit1", method=RequestMethod.POST, params="action=cancel")
    public String cancel() {
        return "tasks/tasksList";
    }

}
