package ru.panic.managementwithdrawalservice.template.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.panic.managementwithdrawalservice.template.dto.CreateWithdrawalMessage;

@RabbitListener(queues = "withdrawal-queue")
public class WithdrawalRabbitListener {
    @RabbitHandler
    private void listenWithdrawals(String message){
        ObjectMapper objectMapper = new ObjectMapper();

        CreateWithdrawalMessage createWithdrawalMessage = objectMapper.convertValue(message, CreateWithdrawalMessage.class);


    }
}
