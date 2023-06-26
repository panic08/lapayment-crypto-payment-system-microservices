package ru.panic.paymentservice.template.exception;

public class AmountBelowMinimumException extends RuntimeException{
    public AmountBelowMinimumException(String message){
        super(message);
    }
}
