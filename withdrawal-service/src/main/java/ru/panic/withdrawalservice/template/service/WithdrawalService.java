package ru.panic.withdrawalservice.template.service;

import ru.panic.withdrawalservice.template.dto.CreateWithdrawalRequest;
import ru.panic.withdrawalservice.template.dto.CreateWithdrawalResponse;

public interface WithdrawalService {
    CreateWithdrawalResponse createWithdrawal(CreateWithdrawalRequest request);
}
