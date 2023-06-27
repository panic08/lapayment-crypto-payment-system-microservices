package ru.panic.managementpaymentservice.template.dto;

import lombok.Getter;
import ru.panic.managementpaymentservice.template.enums.CryptoCurrency;

@Getter
public class PaymentMessage {
    private String walletId;
    private String companyName;
    private String o;
    private Double amount;
    private CryptoCurrency currency;
    private String ipAddress;
    private Long timestamp;
}
