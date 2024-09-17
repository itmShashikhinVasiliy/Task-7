package ru.shashikhin.spring.boot_security.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shashikhin.spring.boot_security.web.dto.UserDTO;
import ru.shashikhin.spring.boot_security.web.models.User;

@RestController
@RequestMapping("/user")
public class UserController {

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<UserDTO> show(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }
}
