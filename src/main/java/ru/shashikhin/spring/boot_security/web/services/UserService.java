package ru.shashikhin.spring.boot_security.web.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.shashikhin.spring.boot_security.web.dto.UserDTO;
import ru.shashikhin.spring.boot_security.web.models.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    void save(User user);
    void update(User user);
    void delete(Long id);
    UserDTO findDTOById(Long id);
    void updateDTO(Long id, UserDTO user);
}
