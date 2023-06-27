package ru.panic.managementpaymentservice.template.service;

import ru.panic.managementpaymentservice.template.dto.PaymentMessage;

public interface PaymentService {
    void handlePayment(PaymentMessage paymentMessage);
}
