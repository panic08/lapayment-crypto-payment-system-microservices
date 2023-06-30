package ru.panic.managementwithdrawalservice.template.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.panic.managementwithdrawalservice.template.dto.CreateWithdrawalMessage;
import ru.panic.managementwithdrawalservice.template.service.impl.WithdrawalServiceImpl;

@RabbitListener(queues = "withdrawal-queue")
public class WithdrawalRabbitListener {
    public WithdrawalRabbitListener(WithdrawalServiceImpl withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    private final WithdrawalServiceImpl withdrawalService;
    @RabbitHandler
    private void listenWithdrawals(String message){
        ObjectMapper objectMapper = new ObjectMapper();

        CreateWithdrawalMessage createWithdrawalMessage = objectMapper.convertValue(message, CreateWithdrawalMessage.class);

        withdrawalService.handleWithdrawal(createWithdrawalMessage);
    }
}
