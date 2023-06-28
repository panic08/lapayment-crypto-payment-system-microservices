package ru.panic.authservice.template.repository.impl;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.panic.authservice.generatedClasses.tables.CompaniesTable;
import ru.panic.authservice.template.entity.Company;
import ru.panic.authservice.template.repository.CompanyRepository;

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
    public Company findCompanyByCompanyName(String companyName) {
        return dslContext.selectFrom(CompaniesTable.COMPANIES_TABLE)
                .where(CompaniesTable.COMPANIES_TABLE.COMPANY_NAME.eq(companyName))
                .fetchOneInto(Company.class);
    }

    @Override
    public boolean extendedBySecretKey(String secretKey) {
        String secretKey1 = dslContext.select(CompaniesTable.COMPANIES_TABLE.SECRET_KEY)
                .from(CompaniesTable.COMPANIES_TABLE)
                .where(CompaniesTable.COMPANIES_TABLE.SECRET_KEY.eq(secretKey))
                .fetchOneInto(String.class);
        return secretKey1 != null;
    }

    @Override
    public List<Company> findAllCompanyByOwner(String username) {
        return dslContext.selectFrom(CompaniesTable.COMPANIES_TABLE)
                .where(CompaniesTable.COMPANIES_TABLE.OWNER.eq(username))
                .fetchInto(Company.class);
    }

    @Override
    public String findOwnerByCompanyName(String companyName) {
        return dslContext.select(CompaniesTable.COMPANIES_TABLE.OWNER)
                .from(CompaniesTable.COMPANIES_TABLE)
                .where(CompaniesTable.COMPANIES_TABLE.COMPANY_NAME.eq(companyName))
                .fetchOneInto(String.class);
    }

    @Override
    public void deleteCompanyByCompanyName(String companyName) {
        dslContext.deleteFrom(CompaniesTable.COMPANIES_TABLE)
                .where(CompaniesTable.COMPANIES_TABLE.COMPANY_NAME.eq(companyName))
                .execute();
    }
}
