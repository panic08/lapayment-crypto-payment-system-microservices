package ru.panic.withdrawalservice.template.repository;

import ru.panic.withdrawalservice.template.entity.Withdrawal;

import java.util.List;

public interface WithdrawalRepository {
    List<Withdrawal> findAllWithdrawalByClientUsername(String clientUsername);
    void deleteWithdrawalById(Long id);
}
