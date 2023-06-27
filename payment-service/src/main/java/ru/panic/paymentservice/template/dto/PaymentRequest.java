package ru.panic.paymentservice.template.dto;

import lombok.Getter;
import ru.panic.paymentservice.template.enums.Currency;

@Getter
public class PaymentRequest {
    private String companyName;
    private Double amount;
    private String o;
    private Currency currency;
    private String ipAddress;
}
