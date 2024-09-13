package ru.shashikhin.spring.boot_security.web.services;

import ru.shashikhin.spring.boot_security.web.models.User;

public interface RegistrationService {
    void register(User user);
}
