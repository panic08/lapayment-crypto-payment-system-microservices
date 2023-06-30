package ru.panic.managementwithdrawalservice.template.dto;

import lombok.Getter;
import lombok.Setter;
import ru.panic.managementwithdrawalservice.template.enums.CryptoCurrency;

@Getter
@Setter
public class CreateWithdrawalMessage {
    private String username;
    private Double amount;
    private CryptoCurrency currency;
    private Long timestamp;
}
