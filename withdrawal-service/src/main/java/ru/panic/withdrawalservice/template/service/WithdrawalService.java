package ru.panic.withdrawalservice.template.service;

import ru.panic.withdrawalservice.template.dto.CreateWithdrawalRequest;
import ru.panic.withdrawalservice.template.dto.CreateWithdrawalResponse;
import ru.panic.withdrawalservice.template.entity.Withdrawal;

import java.util.List;

public interface WithdrawalService {
    CreateWithdrawalResponse createWithdrawal(String jwtToken, CreateWithdrawalRequest request);
    List<Withdrawal> readAllWithdrawalByUsername(String jwtToken);
    void deleteWithdrawal(String jwtToken, Withdrawal withdrawal);
}
