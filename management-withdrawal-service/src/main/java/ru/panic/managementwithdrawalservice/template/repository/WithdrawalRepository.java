package ru.panic.managementwithdrawalservice.template.repository;

import ru.panic.managementwithdrawalservice.template.entity.Withdrawal;

public interface WithdrawalRepository {
    void save(Withdrawal withdrawal);
}
