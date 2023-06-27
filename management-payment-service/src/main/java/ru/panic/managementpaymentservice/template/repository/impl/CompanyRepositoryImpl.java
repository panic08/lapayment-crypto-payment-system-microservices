package ru.panic.managementpaymentservice.template.repository.impl;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.panic.managementpaymentservice.generatedClasses.tables.CompaniesTable;
import ru.panic.managementpaymentservice.template.entity.Company;
import ru.panic.managementpaymentservice.template.repository.CompanyRepository;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
    public CompanyRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
    private final DSLContext dslContext;
    @Override
    public Company findCompanyByCompanyName(String companyName) {
        return dslContext.selectFrom(CompaniesTable.COMPANIES_TABLE)
                .where(CompaniesTable.COMPANIES_TABLE.COMPANY_NAME.eq(companyName))
                .fetchOneInto(Company.class);
    }
}
