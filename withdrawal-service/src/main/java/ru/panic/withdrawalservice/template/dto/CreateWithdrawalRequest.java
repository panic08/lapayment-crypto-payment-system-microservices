package ru.panic.withdrawalservice.template.dto;

import lombok.Getter;
import ru.panic.withdrawalservice.template.enums.CryptoCurrency;

@Getter
public class CreateWithdrawalRequest {
    private String username;
    private Double amount;
    private CryptoCurrency currency;
}
