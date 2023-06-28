package ru.panic.authservice.template.service.impl;

import org.springframework.stereotype.Service;
import ru.panic.authservice.security.jwt.JwtUtil;
import ru.panic.authservice.template.dto.CreateCompanyRequest;
import ru.panic.authservice.template.dto.CreateCompanyResponse;
import ru.panic.authservice.template.dto.DeleteCompanyRequest;
import ru.panic.authservice.template.entity.Company;
import ru.panic.authservice.template.exception.CompanyWasFoundedException;
import ru.panic.authservice.template.exception.InvalidCredentialsException;
import ru.panic.authservice.template.repository.impl.CompanyRepositoryImpl;
import ru.panic.authservice.template.service.CompanyService;
import ru.panic.authservice.util.SymbolsGeneratorUtil;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    public CompanyServiceImpl(CompanyRepositoryImpl companyRepository, JwtUtil jwtUtil) {
        this.companyRepository = companyRepository;
        this.jwtUtil = jwtUtil;
    }

    private final CompanyRepositoryImpl companyRepository;
    private final JwtUtil jwtUtil;

    @Override
    public CreateCompanyResponse createCompany(String jwtToken, CreateCompanyRequest request) {
        if (!jwtUtil.isJwtValid(jwtToken) && jwtUtil.isTokenExpired(jwtToken)){
            throw new InvalidCredentialsException("Неверный JWT токен");
        }

        if (companyRepository.findCompanyByCompanyName(request.getCompanyName()) != null){
            throw new CompanyWasFoundedException("Компания с таким названием уже существует. Придумайте другое название");
        }

        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setOwner(jwtUtil.extractUsername(jwtToken));
        company.setWebhookUrl(request.getWebhookUrl());

        String secretKey = SymbolsGeneratorUtil.generateRandomSymbols();

        if (companyRepository.extendedBySecretKey(secretKey)){
            return createCompany(jwtToken, request);
        }

        company.setSecretKey(secretKey);
        company.setCreatedAt(System.currentTimeMillis());

        companyRepository.save(company);

        CreateCompanyResponse createCompanyResponse = new CreateCompanyResponse();
        createCompanyResponse.setCompanyName(company.getCompanyName());
        createCompanyResponse.setOwner(company.getOwner());
        createCompanyResponse.setWebhookUrl(company.getWebhookUrl());
        createCompanyResponse.setSecretKey(company.getSecretKey());
        createCompanyResponse.setCreatedAt(company.getCreatedAt());

        return createCompanyResponse;
    }

    @Override
    public List<Company> readCompaniesByUsername(String jwtToken) {
        if (!jwtUtil.isJwtValid(jwtToken) && jwtUtil.isTokenExpired(jwtToken)){
            throw new InvalidCredentialsException("Неверный JWT токен");
        }

        return companyRepository.findAllCompanyByOwner(jwtUtil.extractUsername(jwtToken));
    }

    @Override
    public void deleteCompanyByCompanyName(String jwtToken, DeleteCompanyRequest request) {
        if (!jwtUtil.isJwtValid(jwtToken) && jwtUtil.isTokenExpired(jwtToken)){
            throw new InvalidCredentialsException("Неверный JWT токен");
        }

        if (!companyRepository.findOwnerByCompanyName(request.getCompanyName()).equals(jwtUtil.extractUsername(jwtToken))){
            throw new InvalidCredentialsException("Вы не владеете данной компанией");
        }

        companyRepository.deleteCompanyByCompanyName(request.getCompanyName());
    }
}
