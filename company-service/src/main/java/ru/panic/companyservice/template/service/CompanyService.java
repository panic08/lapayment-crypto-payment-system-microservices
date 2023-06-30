package ru.panic.companyservice.template.service;

import ru.panic.companyservice.template.dto.CreateCompanyRequest;
import ru.panic.companyservice.template.dto.CreateCompanyResponse;
import ru.panic.companyservice.template.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> readAllCompanyByOwner(String jwtToken);
    CreateCompanyResponse createCompany(String jwtToken, CreateCompanyRequest companyRequest);
    void deleteCompany(String jwtToken, Company company);
}
