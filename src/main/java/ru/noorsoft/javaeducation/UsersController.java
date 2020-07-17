package ru.noorsoft.javaeducation;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.noorsoft.javaeducation.StringUpper.firstNameUpperCase;
import static ru.noorsoft.javaeducation.StringUpper.lastNameUpperCase;
import static ru.noorsoft.javaeducation.Validations.*;

@Controller
public class UsersController {
    private UserRepository userRepository;
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String greeting(@RequestParam(name = "err", required = false, defaultValue = "")
                                   String err, Model model) {
        model.addAttribute("err", err);
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/add")
    public String addUser(
                          Model model,
                          @RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String telephoneNumber,
                          @RequestParam String email
                          ) {
        User user = new User(firstNameUpperCase(firstName), lastNameUpperCase(lastName), telephoneNumber, email);
        if(numberValidator(telephoneNumber)
                &&emailValidator(email)
                &&fullNameValidator(firstName, lastName)){
            userRepository.save(user);
            return "redirect:/";
        }else {
            String err = "\n" +
                    "Invalid data. Example: jon snow 895067384923 qwe@mail.ru";
            model.addAttribute("err", err);
        }
        return "users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam long id) {
        userRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam long id,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String telephoneNumber,
                             @RequestParam String email) {
        User u = userRepository.findById(id);
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setTelephoneNumber(telephoneNumber);
        u.setEmail(email);
        userRepository.save(u);
        return "redirect:/";
    }
}