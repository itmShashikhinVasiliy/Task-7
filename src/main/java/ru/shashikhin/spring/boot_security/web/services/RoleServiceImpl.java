package ru.shashikhin.spring.boot_security.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shashikhin.spring.boot_security.web.models.Role;
import ru.shashikhin.spring.boot_security.web.repositories.RoleRepository;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    RoleRepository repo;

    @Autowired
    public RoleServiceImpl(RoleRepository repo) {
        this.repo = repo;
    }

    @Override
    public Role findByName(String name) {
        return repo.findByName(name);
    }
}
