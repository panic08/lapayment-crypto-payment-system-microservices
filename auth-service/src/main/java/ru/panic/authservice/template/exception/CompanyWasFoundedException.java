package ru.panic.authservice.template.exception;

public class CompanyWasFoundedException extends RuntimeException{
    public CompanyWasFoundedException(String message){
        super(message);
    }
}
