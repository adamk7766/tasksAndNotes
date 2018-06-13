package pl.gaamit.tasksAndNotes.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import pl.gaamit.tasksAndNotes.model.Task;

import pl.gaamit.tasksAndNotes.model.User;
import pl.gaamit.tasksAndNotes.model.UserService;
import pl.gaamit.tasksAndNotes.repository.TaskRepository;
import pl.gaamit.tasksAndNotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/tasks/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("task", new Task());
        modelMap.addAttribute("users", userRepository.findAll());
        return "tasks/add";
    }

    @PostMapping("/tasks")
    public String save(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

/*    @GetMapping("/tasks")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("tasks",taskRepository.findAll());
        return "tasks/tasksList";
    }*/

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ModelAndView getTasksList(ModelMap modelMap) {
        modelMap.addAttribute("tasks", taskRepository.findAll());
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Cześć " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.setViewName("tasks/tasksList");
        return modelAndView;
    }

    @GetMapping("task/{id}")
    public String show(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("task", taskRepository.findById(id).get());
        return "tasks/show";
    }

    @GetMapping("task/{id}/delete")
    public String delete(@PathVariable Long id, ModelMap modelMap) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping("task/{id}/edit")
    public String edit(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("task", taskRepository.findById(id).get());
        modelMap.addAttribute("users", userRepository.findAll());
        return "tasks/edit";
    }

    @RequestMapping(value = "/edit1", method = RequestMethod.POST, params = "action=cancel")
    public String cancel() {
        return "tasks/tasksList";
    }

}
