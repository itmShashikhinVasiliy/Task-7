package ru.shashikhin.spring.boot_security.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shashikhin.spring.boot_security.web.models.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String show(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }
}
