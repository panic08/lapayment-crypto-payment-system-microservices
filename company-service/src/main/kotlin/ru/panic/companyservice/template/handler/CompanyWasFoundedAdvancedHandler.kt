package ru.panic.companyservice.template.handler

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.panic.companyservice.template.exception.CompanyWasFoundedException

@RestControllerAdvice
class CompanyWasFoundedAdvancedHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CompanyWasFoundedException::class)
    private fun handleCompanyWasFoundedException(companyWasFoundedException: CompanyWasFoundedException): String{
        return companyWasFoundedException.exMessage
    }
}