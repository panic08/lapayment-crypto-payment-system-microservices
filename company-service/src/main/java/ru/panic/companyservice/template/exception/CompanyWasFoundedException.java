package ru.panic.companyservice.template.exception;

public class CompanyWasFoundedException extends RuntimeException{
    public CompanyWasFoundedException(String message){
        super(message);
    }
}
