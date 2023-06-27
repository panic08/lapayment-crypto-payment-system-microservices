package ru.panic.managementpaymentservice.template.repository.impl;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.panic.managementpaymentservice.generatedClasses.tables.PaymentsTable;
import ru.panic.managementpaymentservice.template.entity.Payment;
import ru.panic.managementpaymentservice.template.repository.PaymentRepository;
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    public PaymentRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    private final DSLContext dslContext;
    @Override
    public void save(Payment payment) {
        dslContext.insertInto(PaymentsTable.PAYMENTS_TABLE)
                .set(PaymentsTable.PAYMENTS_TABLE.WALLET_ID, payment.getWalletId())
                .set(PaymentsTable.PAYMENTS_TABLE.COMPANY_NAME, payment.getCompanyName())
                .set(PaymentsTable.PAYMENTS_TABLE.O, payment.getO())
                .set(PaymentsTable.PAYMENTS_TABLE.AMOUNT, payment.getAmount())
                .set(PaymentsTable.PAYMENTS_TABLE.CURRENCY, payment.getCurrency().toString())
                .set(PaymentsTable.PAYMENTS_TABLE.IP_ADDRESS, payment.getIpAddress())
                .set(PaymentsTable.PAYMENTS_TABLE.TIMESTAMP, payment.getTimestamp())
                .execute();
    }
}
