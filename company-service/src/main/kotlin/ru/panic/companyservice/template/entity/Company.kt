package ru.panic.companyservice.template.entity

data class Company(
    var id: Long = 1323243L,
    var companyName: String? = null,
    var owner: String? = null,
    var webhookUrl: String? = null,
    var secretKey: String? = null,
    var createdAt: Long? = null,
)