package ru.panic.managementwithdrawalservice.template.entity;

import lombok.Data;

@Data
public class Client {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Double btc_balance;
    private Double eth_balance;
    private Double ltc_balance;
    private Double trx_balance;
    private Double ton_balance;
    private Double matic_balance;
    private Double xrp_balance;
    private Double tetherERC20_balance;
    private Boolean isAccountNonLocked;
    private Long registeredAt;

}
