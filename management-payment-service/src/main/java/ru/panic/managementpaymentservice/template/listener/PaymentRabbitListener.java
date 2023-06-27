package ru.panic.managementpaymentservice.template.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.panic.managementpaymentservice.template.dto.PaymentMessage;
import ru.panic.managementpaymentservice.template.service.impl.PaymentServiceImpl;

@RabbitListener(queues = "payment-queue")
public class PaymentRabbitListener {
    public PaymentRabbitListener(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }
    private final PaymentServiceImpl paymentService;
    @RabbitHandler
    private void listenPayments(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentMessage paymentMessage = objectMapper.convertValue(message, PaymentMessage.class);

        paymentService.handlePayment(paymentMessage);
    }
}
