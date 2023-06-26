package ru.panic.paymentservice.template.dto;

import lombok.Getter;
import lombok.Setter;
import ru.panic.paymentservice.template.enums.CryptoCurrency;

@Getter
@Setter
public class PaymentResponse {
    private String walletId;
    private Double amount;
    private CryptoCurrency currency;
    private String ipAddress;
    private Long timestamp;
}
