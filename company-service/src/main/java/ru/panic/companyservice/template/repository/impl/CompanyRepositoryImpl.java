package ru.panic.companyservice.template.repository.impl;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.panic.companyservice.generatedClasses.tables.CompaniesTable;
import ru.panic.companyservice.template.entity.Company;
import ru.panic.companyservice.template.repository.CompanyRepository;

import java.util.List;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
    public CompanyRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    private final DSLContext dslContext;

    @Override
    public void save(Company company) {
        dslContext.insertInto(CompaniesTable.COMPANIES_TABLE)
                .set(CompaniesTable.COMPANIES_TABLE.COMPANY_NAME, company.getCompanyName())
                .set(CompaniesTable.COMPANIES_TABLE.OWNER, company.getOwner())
                .set(CompaniesTable.COMPANIES_TABLE.WEBHOOK_URL, company.getWebhookUrl())
                .set(CompaniesTable.COMPANIES_TABLE.SECRET_KEY, company.getSecretKey())
                .set(CompaniesTable.COMPANIES_TABLE.CREATED_AT, company.getCreatedAt())
                .execute();
    }

    @Override
    public void delete(Company company) {
        dslContext.deleteFrom(CompaniesTable.COMPANIES_TABLE)
                .where(CompaniesTable.COMPANIES_TABLE.ID.eq(company.getId()))
                .and(CompaniesTable.COMPANIES_TABLE.COMPANY_NAME.eq(company.getCompanyName()))
                .and(CompaniesTable.COMPANIES_TABLE.OWNER.eq(company.getOwner()))
                .and(CompaniesTable.COMPANIES_TABLE.WEBHOOK_URL.eq(company.getWebhookUrl()))
                .and(CompaniesTable.COMPANIES_TABLE.SECRET_KEY.eq(company.getSecretKey()))
                .and(CompaniesTable.COMPANIES_TABLE.CREATED_AT.eq(company.getCreatedAt()))
                .execute();
    }

    @Override
    public List<Company> findAllCompanyByOwner(String owner) {
        return dslContext.selectFrom(CompaniesTable.COMPANIES_TABLE)
                .where(CompaniesTable.COMPANIES_TABLE.OWNER.eq(owner))
                .fetchInto(Company.class);
    }

    @Override
    public boolean extendedByCompanyName(String companyName) {
        Company company = dslContext.selectFrom(CompaniesTable.COMPANIES_TABLE)
                .where(CompaniesTable.COMPANIES_TABLE.COMPANY_NAME.eq(companyName))
                .fetchOneInto(Company.class);
        return company != null;
    }
}
