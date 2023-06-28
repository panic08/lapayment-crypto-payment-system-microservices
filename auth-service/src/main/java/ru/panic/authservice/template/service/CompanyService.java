package ru.panic.authservice.template.service;

import ru.panic.authservice.template.dto.CreateCompanyRequest;
import ru.panic.authservice.template.dto.CreateCompanyResponse;
import ru.panic.authservice.template.dto.DeleteCompanyRequest;
import ru.panic.authservice.template.entity.Company;

import java.util.List;

public interface CompanyService {
    CreateCompanyResponse createCompany(String jwtToken, CreateCompanyRequest request);
    List<Company> readCompaniesByUsername(String jwtToken);
    void deleteCompanyByCompanyName(String jwtToken, DeleteCompanyRequest request);
}
