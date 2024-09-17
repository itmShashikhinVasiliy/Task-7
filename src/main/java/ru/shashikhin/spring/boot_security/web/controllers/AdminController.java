package ru.shashikhin.spring.boot_security.web.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shashikhin.spring.boot_security.web.dto.UserDTO;
import ru.shashikhin.spring.boot_security.web.exceptions.user_exceptions.UserNotCreatedException;
import ru.shashikhin.spring.boot_security.web.exceptions.user_exceptions.UserNotFoundException;
import ru.shashikhin.spring.boot_security.web.exceptions.user_exceptions.UserNotUpdatedException;
import ru.shashikhin.spring.boot_security.web.models.User;
import ru.shashikhin.spring.boot_security.web.services.RegistrationService;
import ru.shashikhin.spring.boot_security.web.services.UserService;
import ru.shashikhin.spring.boot_security.web.util.UserValidator;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    @Autowired
    public AdminController(UserService userService, RegistrationService registrationService, UserValidator userValidator) {
        this.userService = userService;
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public List<User> index() {
        return userService.findAll();
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid User user,
                                             BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new UserNotCreatedException();
        }

        registrationService.register(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> show(@PathVariable("id") Long id) {
        UserDTO user = userService.findDTOById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO user,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            throw new UserNotUpdatedException();
        }
        userService.updateDTO(id, user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
