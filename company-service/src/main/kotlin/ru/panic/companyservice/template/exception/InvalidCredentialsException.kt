package ru.panic.companyservice.template.exception

data class InvalidCredentialsException(
    val exMessage: String
) : RuntimeException(exMessage)