package ru.panic.companyservice.template.dto

class CreateCompanyResponse(
    val companyName: String? = null,
    val owner: String? = null,
    val webhookUrl: String? = null,
    val secretKey: String? = null,
    val createdAt: Long? = null
)