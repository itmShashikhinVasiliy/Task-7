package ru.shashikhin.spring.boot_security.web.exceptions.user_exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}
