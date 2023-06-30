package ru.panic.companyservice.template.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.panic.companyservice.template.dto.CreateCompanyRequest
import ru.panic.companyservice.template.dto.CreateCompanyResponse
import ru.panic.companyservice.template.dto.DeleteCompanyRequest
import ru.panic.companyservice.template.entity.Company
import ru.panic.companyservice.template.service.impl.CompanyServiceImpl

@RestController
@RequestMapping("/api/company")
class CompanyController(companyService: CompanyServiceImpl) {
    @PostMapping("/create")
    private fun createCompany(@RequestHeader jwtToken: String,
                              @RequestBody createCompanyRequest: CreateCompanyRequest): CreateCompanyResponse?{
        return createCompany(jwtToken, createCompanyRequest)
    }
    @GetMapping("/readAllByUsername")
    private fun readAllByUsername(@RequestHeader jwtToken: String): List<Company>?{
        return readAllByUsername(jwtToken)
    }
    @DeleteMapping("/deleteByCompanyName")
    private fun deleteByCompanyName(
        @RequestHeader jwtToken: String,
        @RequestBody deleteCompanyRequest: DeleteCompanyRequest
    ){
        deleteByCompanyName(jwtToken, deleteCompanyRequest)
    }
}