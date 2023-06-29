package ru.panic.withdrawalservice.template.dto;

import lombok.Setter;
import ru.panic.withdrawalservice.template.enums.CryptoCurrency;
@Setter
public class CreateWithdrawalResponse {
    private String username;
    private Double amount;
    private CryptoCurrency currency;
    private Long timestamp;
}
