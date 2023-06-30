package ru.panic.companyservice.template.service

import ru.panic.companyservice.template.dto.CreateCompanyRequest
import ru.panic.companyservice.template.dto.CreateCompanyResponse
import ru.panic.companyservice.template.dto.DeleteCompanyRequest
import ru.panic.companyservice.template.entity.Company

interface CompanyService {
    fun createCompany(jwtToken: String, createCompanyRequest: CreateCompanyRequest): CreateCompanyResponse
    fun readAllByUsername(jwtToken: String): List<Company>?
    fun deleteByCompanyName(jwtToken: String, deleteCompanyRequest: DeleteCompanyRequest)
}