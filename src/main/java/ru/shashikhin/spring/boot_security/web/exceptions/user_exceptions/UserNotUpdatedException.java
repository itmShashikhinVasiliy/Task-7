package ru.shashikhin.spring.boot_security.web.exceptions.user_exceptions;

public class UserNotUpdatedException extends RuntimeException {
    public UserNotUpdatedException() {
        super("User not updated");
    }
}
