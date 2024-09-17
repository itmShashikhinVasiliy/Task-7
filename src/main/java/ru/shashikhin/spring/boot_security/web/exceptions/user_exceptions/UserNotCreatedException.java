package ru.shashikhin.spring.boot_security.web.exceptions.user_exceptions;

public class UserNotCreatedException extends RuntimeException{
    public UserNotCreatedException() {
        super("User not created");
    }
}
