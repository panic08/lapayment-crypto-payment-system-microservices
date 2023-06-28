package ru.panic.authservice.template.dto;

import lombok.Getter;

@Getter
public class CreateCompanyRequest {
    private String companyName;
    private String webhookUrl;
}
