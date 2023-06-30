package ru.panic.companyservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CompanyServiceApplication
fun main(args: Array<String>) {
    runApplication<CompanyServiceApplication>(*args)
}