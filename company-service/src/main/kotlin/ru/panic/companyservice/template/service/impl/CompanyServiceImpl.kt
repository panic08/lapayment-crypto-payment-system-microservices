package ru.panic.companyservice.template.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import ru.panic.companyservice.template.dto.CreateCompanyRequest
import ru.panic.companyservice.template.dto.CreateCompanyResponse
import ru.panic.companyservice.template.dto.DeleteCompanyRequest
import ru.panic.companyservice.template.entity.Client
import ru.panic.companyservice.template.entity.Company
import ru.panic.companyservice.template.exception.CompanyWasFoundedException
import ru.panic.companyservice.template.exception.InvalidCredentialsException
import ru.panic.companyservice.template.repository.impl.CompanyRepositoryImpl
import ru.panic.companyservice.template.service.CompanyService
import ru.panic.companyservice.util.SymbolsGeneratorUtil

@Service
class CompanyServiceImpl(private val companyRepository: CompanyRepositoryImpl,
                         private val restTemplate: RestTemplate) : CompanyService {
    override fun createCompany(jwtToken: String, createCompanyRequest: CreateCompanyRequest): CreateCompanyResponse {
        val responseClient: ResponseEntity<Client> = restTemplate.postForEntity(
            "http://localhost:8080/api/auth/getInfoByJwt?jwtToken=$jwtToken",
            null,
            Client::class.java
        )

        if (responseClient.statusCode.isError){
            throw InvalidCredentialsException("Некорректный JWT токен")
        }

        val company: Company? = companyRepository.findCompanyByCompanyName(createCompanyRequest.companyName)

        if (company != null) {
            throw CompanyWasFoundedException("Название компании с таким названием уже существует")
        }

        val newCompany: Company = Company()
        newCompany.companyName = createCompanyRequest.companyName
        newCompany.webhookUrl = createCompanyRequest.webhookUrl
        newCompany.owner = responseClient.body?.username

        val secretKey = SymbolsGeneratorUtil.generateRandomSymbols()

        if (companyRepository.extendedBySecretKey(secretKey)){
            return createCompany(jwtToken, createCompanyRequest)
        }

        newCompany.secretKey = secretKey
        newCompany.createdAt = System.currentTimeMillis()

        companyRepository.save(newCompany)

        return CreateCompanyResponse(
            createCompanyRequest.companyName,
            responseClient.body?.username,
            createCompanyRequest.webhookUrl,
            newCompany.secretKey,
            newCompany.createdAt
        )
    }

    override fun readAllByUsername(jwtToken: String): List<Company>? {
        val responseClient: ResponseEntity<Client> = restTemplate.postForEntity(
            "http://localhost:8080/api/auth/getInfoByJwt?jwtToken=$jwtToken",
            null,
            Client::class.java
        )

        if (responseClient.statusCode.isError){
            throw InvalidCredentialsException("Некорректный JWT токен")
        }

        val client: Client = responseClient.body!!

        return companyRepository.findAllCompanyByOwner(client.username!!)

    }

    override fun deleteByCompanyName(jwtToken: String, deleteCompanyRequest: DeleteCompanyRequest) {
        val responseClient: ResponseEntity<Client> = restTemplate.postForEntity(
            "http://localhost:8080/api/auth/getInfoByJwt?jwtToken=$jwtToken",
            null,
            Client::class.java
        )

        if (responseClient.statusCode.isError){
            throw InvalidCredentialsException("Некорректный JWT токен")
        }

        val client: Client? = responseClient.body
        val company: Company = companyRepository.findCompanyByCompanyName(deleteCompanyRequest.companyName)
            ?: throw InvalidCredentialsException("Вы не можете удалить эту компанию")

        if (!company.owner.equals(client!!.username)){
            throw InvalidCredentialsException("Вы не можете удалить эту компанию")
        }

        companyRepository.deleteByCompanyName(company.companyName!!)

    }
}