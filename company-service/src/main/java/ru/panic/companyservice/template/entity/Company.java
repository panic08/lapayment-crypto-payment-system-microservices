package ru.panic.companyservice.template.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
public class Company {
    private Long id;
    @Column(value = "company_name")
    private String companyName;
    private String owner;
    @Column(value = "webhook_url")
    private String webhookUrl;
    @Column(value = "secret_key")
    private String secretKey;
    @Column(value = "created_at")
    private Long createdAt;
}
