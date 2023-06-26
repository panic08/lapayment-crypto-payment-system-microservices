package ru.panic.paymentservice.template.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.panic.paymentservice.template.exception.AmountBelowMinimumException;

@RestControllerAdvice
public class AmountBelowMinimumAdvancedHandler {
    @ResponseStatus
    @ResponseBody
    @ExceptionHandler(AmountBelowMinimumException.class)
    private String handleAmountBelowMinimumException(AmountBelowMinimumException amountBelowMinimumException){
        return amountBelowMinimumException.getMessage();
    }
}
