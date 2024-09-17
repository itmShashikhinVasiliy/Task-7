package ru.shashikhin.spring.boot_security.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.shashikhin.spring.boot_security.web.exceptions.user_exceptions.UserNotCreatedException;
import ru.shashikhin.spring.boot_security.web.exceptions.user_exceptions.UserNotFoundException;
import ru.shashikhin.spring.boot_security.web.exceptions.user_exceptions.UserNotUpdatedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotCreatedException.class)
    public ResponseEntity<String> handleException(UserNotCreatedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotUpdatedException.class)
    public ResponseEntity<String> handleException(UserNotUpdatedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
