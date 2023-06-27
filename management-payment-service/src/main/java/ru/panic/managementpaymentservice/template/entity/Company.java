package ru.panic.managementpaymentservice.template.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
    private Long id;
    private String companyName;
    private String owner;
    private String webhookUrl;
    private String secretKey;
    private Long createdAt;
}
