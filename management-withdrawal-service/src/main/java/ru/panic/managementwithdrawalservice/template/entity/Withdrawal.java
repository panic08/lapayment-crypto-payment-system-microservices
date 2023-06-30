package ru.panic.managementwithdrawalservice.template.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import ru.panic.managementwithdrawalservice.template.enums.CryptoCurrency;

@Getter
@Setter
public class Withdrawal {
    private Long id;
    @Column(value = "client_username")
    private String clientUsername;
    private Double amount;
    private CryptoCurrency currency;
    private Long timestamp;
}
