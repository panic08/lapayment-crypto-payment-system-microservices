package ru.panic.authservice.template.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.panic.authservice.template.exception.CompanyWasFoundedException;

@RestControllerAdvice
public class CompanyWasFoundedAdvancedHandler {
    @ResponseBody
    @ResponseStatus
    @ExceptionHandler(CompanyWasFoundedException.class)
    private String handleCompanyWasFoundedException(CompanyWasFoundedException companyWasFoundedException){
        return companyWasFoundedException.getMessage();
    }
}
