package ru.panic.withdrawalservice.template.entity;

import lombok.Getter;
import lombok.Setter;
import ru.panic.withdrawalservice.template.enums.CryptoCurrency;
@Getter
@Setter
public class Withdrawal {
    private String clientUsername;
    private Double amount;
    private CryptoCurrency currency;
    private Long timestamp;
}
