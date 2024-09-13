package ru.shashikhin.spring.boot_security.web.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.shashikhin.spring.boot_security.web.models.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    void save(User user);
    void update(Long id, User user);
    void delete(Long id);
}
