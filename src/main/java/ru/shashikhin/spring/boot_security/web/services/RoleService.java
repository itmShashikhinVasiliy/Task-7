package ru.shashikhin.spring.boot_security.web.services;

import ru.shashikhin.spring.boot_security.web.models.Role;

public interface RoleService {
    Role findByName(String name);
}
