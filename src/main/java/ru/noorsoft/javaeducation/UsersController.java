package ru.noorsoft.javaeducation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class UsersController {
    private UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World")
                                   String name, Model model) {
        model.addAttribute("name", name);
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String firstName, @RequestParam String lastName) {
        User user = new User(firstName, lastName);
        userRepository.save(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam long id) {
        userRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam long id, @RequestParam String firstName, @RequestParam String lastName) {
        User u = userRepository.findById(id);
        u.setFirstName(firstName);
        u.setLastName(lastName);
        userRepository.save(u);
        System.out.println(u.toString());
        return "redirect:/";
    }
}