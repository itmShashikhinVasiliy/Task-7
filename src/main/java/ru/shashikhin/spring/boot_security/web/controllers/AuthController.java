package ru.shashikhin.spring.boot_security.web.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.shashikhin.spring.boot_security.web.models.User;
import ru.shashikhin.spring.boot_security.web.services.RegistrationService;
import ru.shashikhin.spring.boot_security.web.util.UserValidator;

@Controller
public class AuthController {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, UserValidator userValidator) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String index() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        registrationService.register(user);
        return "redirect:/login";
    }
}
