package ru.panic.companyservice.template.repository;

import ru.panic.companyservice.template.entity.Company;

import java.util.List;

public interface CompanyRepository {
    void save(Company company);
    void delete(Company company);
    List<Company> findAllCompanyByOwner(String owner);
    boolean extendedByCompanyName(String companyName);
}
