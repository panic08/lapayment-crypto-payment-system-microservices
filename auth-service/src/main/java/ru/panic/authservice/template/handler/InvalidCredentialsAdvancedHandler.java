package ru.panic.authservice.template.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.panic.authservice.template.exception.InvalidCredentialsException;

@RestControllerAdvice
public class InvalidCredentialsAdvancedHandler {
    @ResponseBody
    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    private String handleInvalidCredentialsException(InvalidCredentialsException invalidCredentialsException){
        return invalidCredentialsException.getMessage();
    }
}
