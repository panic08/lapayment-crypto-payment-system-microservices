package ru.panic.companyservice.template.controller;

import org.springframework.web.bind.annotation.*;
import ru.panic.companyservice.template.dto.CreateCompanyRequest;
import ru.panic.companyservice.template.dto.CreateCompanyResponse;
import ru.panic.companyservice.template.entity.Company;
import ru.panic.companyservice.template.service.impl.CompanyServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    private final CompanyServiceImpl companyService;
    @GetMapping("/readAllCompanyByOwner")
    private List<Company> readAllCompanyByOwner(@RequestHeader String jwtToken){
        return companyService.readAllCompanyByOwner(jwtToken);
    }
    @PostMapping("/create")
    private CreateCompanyResponse createCompany(
            @RequestHeader String jwtToken,
            @RequestBody CreateCompanyRequest companyRequest
    ){
        return companyService.createCompany(jwtToken, companyRequest);
    }
    @DeleteMapping("/delete")
    private void deleteCompany(
            @RequestHeader String jwtToken,
            @RequestBody Company company
    ){

    }
}
