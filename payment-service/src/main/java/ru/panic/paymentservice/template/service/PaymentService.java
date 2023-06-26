package ru.panic.paymentservice.template.service;

import ru.panic.paymentservice.template.dto.PaymentRequest;
import ru.panic.paymentservice.template.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse handlePaymentByBitcoin(PaymentRequest request);
    PaymentResponse handlePaymentByEthereum(PaymentRequest request);
    PaymentResponse handlePaymentByLitecoin(PaymentRequest request);
    PaymentResponse handlePaymentByTron(PaymentRequest request);
    PaymentResponse handlePaymentByTon(PaymentRequest request);
    PaymentResponse handlePaymentByMatic(PaymentRequest request);
    PaymentResponse handlePaymentByRipple(PaymentRequest request);
    PaymentResponse handlePaymentByTetherERC20(PaymentRequest request);
}
