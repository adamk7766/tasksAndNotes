package pl.gaamit.tasksAndNotes.controller;


import pl.gaamit.tasksAndNotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("users", userRepository.findAll());
        return "users/usersList";
    }

    @GetMapping("user/{id}/edit")
    public String edit(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("user", userRepository.findById(id).get());
        return "users/edit";
    }

    @GetMapping("users/{id}")
    public String show(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("user", userRepository.findById(id).get());
        return "users/show";
    }


    @GetMapping("users/{id}/delete")
    public String delete(@PathVariable Long id, ModelMap modelMap) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }


}
