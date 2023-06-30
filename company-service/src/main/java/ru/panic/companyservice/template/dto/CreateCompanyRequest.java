package ru.panic.companyservice.template.dto;

import lombok.Getter;

@Getter
public class CreateCompanyRequest {
    private String companyName;
    private String webhookUrl;
}
