package ru.panic.managementpaymentservice.template.repository;

import ru.panic.managementpaymentservice.template.entity.Payment;

public interface PaymentRepository {
    void save(Payment payment);
}
