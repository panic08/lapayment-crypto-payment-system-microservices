package ru.panic.companyservice.template.exception

class CompanyWasFoundedException(
    val exMessage: String
): RuntimeException(exMessage)