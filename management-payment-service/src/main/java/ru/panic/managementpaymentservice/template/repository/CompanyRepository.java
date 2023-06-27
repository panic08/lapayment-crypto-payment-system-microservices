package ru.panic.managementpaymentservice.template.repository;

import ru.panic.managementpaymentservice.template.entity.Company;

public interface CompanyRepository {
    Company findCompanyByCompanyName(String companyName);
}
