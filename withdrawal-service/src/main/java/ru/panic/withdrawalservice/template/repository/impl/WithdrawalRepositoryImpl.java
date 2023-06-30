package ru.panic.withdrawalservice.template.repository.impl;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.panic.withdrawalservice.generatedClasses.tables.WithdrawalsTable;
import ru.panic.withdrawalservice.template.entity.Withdrawal;
import ru.panic.withdrawalservice.template.repository.WithdrawalRepository;

import java.util.List;

@Repository
public class WithdrawalRepositoryImpl implements WithdrawalRepository {
    public WithdrawalRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
    private final DSLContext dslContext;
    @Override
    public List<Withdrawal> findAllWithdrawalByClientUsername(String clientUsername) {
        return dslContext.selectFrom(WithdrawalsTable.WITHDRAWALS_TABLE)
                .where(WithdrawalsTable.WITHDRAWALS_TABLE.CLIENT_USERNAME.eq(clientUsername))
                .fetchInto(Withdrawal.class);
    }

    @Override
    public void deleteWithdrawalById(Long id) {
        dslContext.deleteFrom(WithdrawalsTable.WITHDRAWALS_TABLE)
                .where(WithdrawalsTable.WITHDRAWALS_TABLE.ID.eq(id))
                .execute();
    }
}
