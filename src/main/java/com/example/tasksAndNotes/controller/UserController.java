package com.example.tasksAndNotes.controller;

import com.example.tasksAndNotes.model.User;
import com.example.tasksAndNotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/add")
    public String add(ModelMap modelMap){
        modelMap.put("user",new User());
        return "users/add";
    }

    @PostMapping("/users")
    public String save(@ModelAttribute User user){
        userRepository.save(user);
        return "redirect:/users";
    }


    @GetMapping("/users")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("users",userRepository.findAll());
        return "users/index";
    }

    @GetMapping("user/{id}/edit")
    public String edit(@PathVariable Long id,ModelMap modelMap){
        modelMap.put("user",userRepository.findById(id).get());
        return "users/add";
    }

    @GetMapping("users/{id}")
    public String show(@PathVariable Long id, ModelMap modelMap){
        modelMap.put("user",userRepository.findById(id).get());
        return "users/show";
    }


    @GetMapping("users/{id}/delete")
    public String delete(@PathVariable Long id,ModelMap modelMap){
        userRepository.deleteById(id);
        return "redirect:/users";
    }


}
