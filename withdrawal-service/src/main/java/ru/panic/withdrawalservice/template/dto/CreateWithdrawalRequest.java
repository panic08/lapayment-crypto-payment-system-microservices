package ru.panic.withdrawalservice.template.dto;

import lombok.Getter;
import ru.panic.withdrawalservice.template.enums.CryptoCurrency;

@Getter
public class CreateWithdrawalRequest {
    private Double amount;
    private CryptoCurrency currency;
}
