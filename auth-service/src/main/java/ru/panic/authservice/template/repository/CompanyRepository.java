package ru.panic.authservice.template.repository;

import ru.panic.authservice.template.entity.Company;

import java.util.List;

public interface CompanyRepository {
    void save(Company company);
    Company findCompanyByCompanyName(String companyName);
    boolean extendedBySecretKey(String secretKey);
    List<Company> findAllCompanyByOwner(String username);
    String findOwnerByCompanyName(String companyName);
    void deleteCompanyByCompanyName(String companyName);
}
