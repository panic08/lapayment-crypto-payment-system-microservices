package ru.panic.authservice.template.dto;

import lombok.Setter;

@Setter
public class CreateCompanyResponse {
    private String companyName;
    private String owner;
    private String webhookUrl;
    private String secretKey;
    private Long createdAt;
}
