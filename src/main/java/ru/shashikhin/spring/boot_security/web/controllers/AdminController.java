package ru.shashikhin.spring.boot_security.web.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shashikhin.spring.boot_security.web.models.User;
import ru.shashikhin.spring.boot_security.web.services.RegistrationService;
import ru.shashikhin.spring.boot_security.web.services.UserService;
import ru.shashikhin.spring.boot_security.web.util.UserValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    private final String REDIRECT_HOME = "redirect:/admin";

    @Autowired
    public AdminController(UserService userService, RegistrationService registrationService, UserValidator userValidator) {
        this.userService = userService;
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String index(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", user);
        return "admin/index";
    }

    @GetMapping("new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/new";
        }
        registrationService.register(user);
        return REDIRECT_HOME;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "admin/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         @PathVariable("id") Long id,
                         BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }
        userService.update(id, user);
        return REDIRECT_HOME;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return REDIRECT_HOME;
    }
}
