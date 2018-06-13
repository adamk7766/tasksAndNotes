package pl.gaamit.tasksAndNotes.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import pl.gaamit.tasksAndNotes.model.Note;
import pl.gaamit.tasksAndNotes.model.User;
import pl.gaamit.tasksAndNotes.model.UserService;
import pl.gaamit.tasksAndNotes.repository.NoteRepository;
import pl.gaamit.tasksAndNotes.repository.UserRepository;
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

    @Autowired
    private UserService userService;


    @GetMapping("/notes/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("note", new Note());
        modelMap.addAttribute("users", userRepository.findAll());
        return "notes/add";
    }

    @PostMapping("/notes")
    public String save(@ModelAttribute Note note) {
        noteRepository.save(note);
        return "redirect:/notes";
    }

/*    @GetMapping("/notes")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("notes",noteRepository.findAll());
        return "notes/notesList";
    }*/

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public ModelAndView getNotesList(ModelMap modelMap) {
        modelMap.addAttribute("notes", noteRepository.findAll());
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Cześć " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.setViewName("notes/notesList");
        return modelAndView;
    }

    @GetMapping("note/{id}")
    public String show(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("note", noteRepository.findById(id).get());
        return "notes/show";
    }

    @GetMapping("note/{id}/delete")
    public String delete(@PathVariable Long id, ModelMap modelMap) {
        noteRepository.deleteById(id);
        return "redirect:/notes";
    }

    @GetMapping("note/{id}/edit")
    public String edit(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("note", noteRepository.findById(id).get());
        modelMap.addAttribute("users", userRepository.findAll());
        return "notes/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "action=cancel")
    public String cancel() {
        return "notes/notesList";
    }


}
