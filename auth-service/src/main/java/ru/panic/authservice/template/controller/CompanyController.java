package ru.panic.authservice.template.controller;

import org.springframework.web.bind.annotation.*;
import ru.panic.authservice.template.dto.CreateCompanyRequest;
import ru.panic.authservice.template.dto.CreateCompanyResponse;
import ru.panic.authservice.template.dto.DeleteCompanyRequest;
import ru.panic.authservice.template.entity.Company;
import ru.panic.authservice.template.service.impl.CompanyServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    private final CompanyServiceImpl companyService;
    @PostMapping("/create")
    private CreateCompanyResponse createCompany(
            @RequestHeader String jwtToken,
            @RequestBody CreateCompanyRequest companyRequest
    ){
        return companyService.createCompany(jwtToken, companyRequest);
    }
    @GetMapping("/readByUsername")
    private List<Company> readCompaniesByUsername(@RequestHeader String jwtToken){
        return companyService.readCompaniesByUsername(jwtToken);
    }
    @DeleteMapping("/deleteByCompanyName")
    private void deleteByCompanyName(
            @RequestHeader String jwtToken,
            @RequestBody DeleteCompanyRequest deleteCompanyRequest
    ){
        companyService.deleteCompanyByCompanyName(jwtToken, deleteCompanyRequest);
    }
}
