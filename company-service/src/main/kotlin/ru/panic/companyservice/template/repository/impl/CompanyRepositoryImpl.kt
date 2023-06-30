package ru.panic.companyservice.template.repository.impl

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.panic.companyservice.generatedClasses.tables.CompaniesTable
import ru.panic.companyservice.template.entity.Company
import ru.panic.companyservice.template.repository.CompanyRepository
@Repository
class CompanyRepositoryImpl(private val dslContext: DSLContext) : CompanyRepository {
    override fun save(company: Company) {
        dslContext.insertInto(CompaniesTable.COMPANIES_TABLE)
            .set(CompaniesTable.COMPANIES_TABLE.COMPANY_NAME, company.companyName)
            .set(CompaniesTable.COMPANIES_TABLE.OWNER, company.owner)
            .set(CompaniesTable.COMPANIES_TABLE.WEBHOOK_URL, company.webhookUrl)
            .set(CompaniesTable.COMPANIES_TABLE.SECRET_KEY, company.secretKey)
            .set(CompaniesTable.COMPANIES_TABLE.COMPANY_NAME, company.companyName)
            .execute()
    }
    override fun findCompanyByCompanyName(companyName: String): Company? {
        return dslContext.selectFrom(CompaniesTable.COMPANIES_TABLE)
            .where(CompaniesTable.COMPANIES_TABLE.COMPANY_NAME.eq(companyName))
            .fetchOneInto(Company::class.java)
    }

    override fun deleteByCompanyName(companyName: String) {
        dslContext.deleteFrom(CompaniesTable.COMPANIES_TABLE)
            .where(CompaniesTable.COMPANIES_TABLE.COMPANY_NAME.eq(companyName))
            .execute()
    }

    override fun extendedBySecretKey(secretKey: String): Boolean {
        val company: List<Company?> = dslContext.selectFrom(CompaniesTable.COMPANIES_TABLE)
            .where(CompaniesTable.COMPANIES_TABLE.SECRET_KEY.eq(secretKey))
            .fetchInto(Company::class.java)
        return company.isEmpty()
    }

    override fun findAllCompanyByOwner(owner: String): List<Company>? {
        return dslContext.selectFrom(CompaniesTable.COMPANIES_TABLE)
            .where(CompaniesTable.COMPANIES_TABLE.OWNER.eq(owner))
            .fetchInto(Company::class.java)
    }
}