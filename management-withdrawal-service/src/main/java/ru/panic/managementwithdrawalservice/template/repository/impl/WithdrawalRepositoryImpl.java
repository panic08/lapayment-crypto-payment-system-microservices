package ru.panic.managementwithdrawalservice.template.repository.impl;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.panic.managementwithdrawalservice.generatedClasses.tables.WithdrawalsTable;
import ru.panic.managementwithdrawalservice.template.entity.Withdrawal;
import ru.panic.managementwithdrawalservice.template.repository.WithdrawalRepository;
@Repository
public class WithdrawalRepositoryImpl implements WithdrawalRepository {
    public WithdrawalRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
    private final DSLContext dslContext;
    @Override
    public void save(Withdrawal withdrawal) {
        dslContext.insertInto(WithdrawalsTable.WITHDRAWALS_TABLE)
                .set(WithdrawalsTable.WITHDRAWALS_TABLE.CLIENT_USERNAME, withdrawal.getClientUsername())
                .set(WithdrawalsTable.WITHDRAWALS_TABLE.AMOUNT, withdrawal.getAmount())
                .set(WithdrawalsTable.WITHDRAWALS_TABLE.CURRENCY, withdrawal.getCurrency().toString())
                .set(WithdrawalsTable.WITHDRAWALS_TABLE.TIMESTAMP, withdrawal.getTimestamp())
                .execute();
    }
}
