package ru.panic.companyservice.template.handler

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.panic.companyservice.template.exception.InvalidCredentialsException

@RestControllerAdvice
class InvalidCredentialsAdvancedHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(InvalidCredentialsException::class)
    private fun handleInvalidCredentialsException(invalidCredentialsException: InvalidCredentialsException): String {
        return invalidCredentialsException.exMessage
    }
}