package ru.panic.managementwithdrawalservice.template.service;

import ru.panic.managementwithdrawalservice.template.dto.CreateWithdrawalMessage;

public interface WithdrawalService {
    void handleWithdrawal(CreateWithdrawalMessage createWithdrawalMessage);
}
