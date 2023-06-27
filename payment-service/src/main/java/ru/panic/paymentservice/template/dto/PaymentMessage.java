package ru.panic.paymentservice.template.dto;

import lombok.Setter;
import ru.panic.paymentservice.template.enums.CryptoCurrency;

@Setter
public class PaymentMessage {
    private String walletId;
    private String companyName;
    private String o;
    private Double amount;
    private CryptoCurrency currency;
    private String ipAddress;
    private Long timestamp;
}
