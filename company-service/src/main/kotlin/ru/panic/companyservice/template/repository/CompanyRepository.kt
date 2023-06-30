package ru.panic.companyservice.template.repository

import ru.panic.companyservice.template.entity.Company

interface CompanyRepository {
    fun save(company: Company)
    fun findCompanyByCompanyName(companyName: String): Company?
    fun deleteByCompanyName(companyName: String)
    fun extendedBySecretKey(secretKey: String): Boolean
    fun findAllCompanyByOwner(owner: String): List<Company>?
}