package ru.shashikhin.spring.boot_security.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shashikhin.spring.boot_security.web.models.User;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
public class RegistrationServiceImpl implements RegistrationService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServiceImpl(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void register(User user) {
        user.setRoles(Collections.singleton(roleService.findByName("ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
    }
}
