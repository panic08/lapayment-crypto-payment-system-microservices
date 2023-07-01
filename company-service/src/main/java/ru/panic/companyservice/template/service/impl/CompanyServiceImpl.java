package ru.panic.companyservice.template.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.panic.companyservice.template.dto.CreateCompanyRequest;
import ru.panic.companyservice.template.dto.CreateCompanyResponse;
import ru.panic.companyservice.template.entity.Client;
import ru.panic.companyservice.template.entity.Company;
import ru.panic.companyservice.template.exception.CompanyWasFoundedException;
import ru.panic.companyservice.template.exception.InvalidCredentialsException;
import ru.panic.companyservice.template.repository.impl.CompanyRepositoryImpl;
import ru.panic.companyservice.template.service.CompanyService;
import ru.panic.companyservice.util.SymbolsGeneratorUtil;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    public CompanyServiceImpl(CompanyRepositoryImpl companyRepository, RestTemplate restTemplate) {
        this.companyRepository = companyRepository;
        this.restTemplate = restTemplate;
    }

    private final CompanyRepositoryImpl companyRepository;
    private final RestTemplate restTemplate;
    @Override
    public List<Company> readAllCompanyByOwner(String jwtToken) {
        ResponseEntity<Client> clientResponseEntity =
                restTemplate.postForEntity(
                        "http://localhost:80/api/auth/getInfoByJwt?jwtToken=" + jwtToken,
                        null,
                        Client.class
                );
        if (clientResponseEntity.getStatusCode().isError()){
            throw new InvalidCredentialsException("Incorrect JWT token");
        }

        return companyRepository.findAllCompanyByOwner(clientResponseEntity.getBody().getUsername());
    }

    @Override
    public CreateCompanyResponse createCompany(String jwtToken, CreateCompanyRequest companyRequest) {
        ResponseEntity<Client> clientResponseEntity =
                restTemplate.postForEntity(
                        "http://localhost:80/api/auth/getInfoByJwt?jwtToken=" + jwtToken,
                        null,
                        Client.class
                );
        if (clientResponseEntity.getStatusCode().isError()){
            throw new InvalidCredentialsException("Incorrect JWT token");
        }

        if (companyRequest.getCompanyName().length() < 5){
            throw new InvalidCredentialsException("The company name can contain only 5 characters or more");
        }


        if (companyRepository.extendedByCompanyName(companyRequest.getCompanyName())){
            throw new CompanyWasFoundedException("A company with the same name already exists");
        }

        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setOwner(clientResponseEntity.getBody().getUsername());
        company.setWebhookUrl(companyRequest.getWebhookUrl());
        company.setSecretKey(SymbolsGeneratorUtil.generateRandomSymbols());
        company.setCreatedAt(System.currentTimeMillis());

        companyRepository.save(company);

        CreateCompanyResponse createCompanyResponse = new CreateCompanyResponse();
        createCompanyResponse.setCompanyName(company.getCompanyName());
        createCompanyResponse.setOwner(clientResponseEntity.getBody().getUsername());
        createCompanyResponse.setWebhookUrl(company.getWebhookUrl());
        createCompanyResponse.setSecretKey(company.getSecretKey());
        createCompanyResponse.setCreatedAt(company.getCreatedAt());

        return createCompanyResponse;
    }

    @Override
    public void deleteCompany(String jwtToken, Company company) {
        ResponseEntity<Client> clientResponseEntity =
                restTemplate.postForEntity(
                        "http://localhost:80/api/auth/getInfoByJwt?jwtToken=" + jwtToken,
                        null,
                        Client.class
                );
        if (clientResponseEntity.getStatusCode().isError()){
            throw new InvalidCredentialsException("Incorrect JWT token");
        }

        if (!clientResponseEntity.getBody().getUsername().equals(company.getOwner())){
            throw new InvalidCredentialsException("You cannot delete this company");
        }

        companyRepository.delete(company);
    }
}
