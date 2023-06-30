package ru.panic.managementpaymentservice.template.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import ru.panic.managementpaymentservice.template.enums.CryptoCurrency;

@Getter
@Setter
public class Payment {
    private Long id;
    @Column(value = "wallet_id")
    private String walletId;
    @Column(value = "company_name")
    private String companyName;
    private String o;
    private Double amount;
    private CryptoCurrency currency;
    @Column(value = "ip_address")
    private String ipAddress;
    private Long timestamp;
}
