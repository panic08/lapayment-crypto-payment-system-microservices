package ru.panic.managementpaymentservice.template.entity;

import lombok.Getter;
import lombok.Setter;
import ru.panic.managementpaymentservice.template.enums.CryptoCurrency;

@Getter
@Setter
public class Payment {
    private Long id;
    private String walletId;
    private String companyName;
    private String o;
    private Double amount;
    private CryptoCurrency currency;
    private String ipAddress;
    private Long timestamp;
}
