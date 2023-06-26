package ru.panic.paymentservice.template.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.panic.paymentservice.template.exception.InvalidCredentialsException;

@RestControllerAdvice
public class InvalidCredentialsAdvancedHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(InvalidCredentialsException.class)
    private String handleInvalidCredentialsException(InvalidCredentialsException invalidCredentialsException){
        return invalidCredentialsException.getMessage();
    }
}
