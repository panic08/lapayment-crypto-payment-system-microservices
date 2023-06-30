package ru.panic.companyservice.template.dto;

import lombok.Setter;
import ru.panic.companyservice.template.enums.CryptoCurrency;

@Setter
public class CreateCompanyResponse {
    private String companyName;
    private String owner;
    private String webhookUrl;
    private String secretKey;
    private Long createdAt;
}
