package ru.panic.paymentservice.template.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.panic.paymentservice.template.exception.AmountBelowMinimumException;

@RestControllerAdvice
public class AmountBelowMinimumAdvancedHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AmountBelowMinimumException.class)
    private String handleAmountBelowMinimumException(AmountBelowMinimumException amountBelowMinimumException){
        return amountBelowMinimumException.getMessage();
    }
}
