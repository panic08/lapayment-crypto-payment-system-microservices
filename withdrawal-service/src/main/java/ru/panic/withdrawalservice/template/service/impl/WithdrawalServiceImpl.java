package ru.panic.withdrawalservice.template.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.panic.withdrawalservice.template.dto.CreateWithdrawalRequest;
import ru.panic.withdrawalservice.template.dto.CreateWithdrawalResponse;
import ru.panic.withdrawalservice.template.entity.Client;
import ru.panic.withdrawalservice.template.entity.Withdrawal;
import ru.panic.withdrawalservice.template.exception.InvalidCredentialsException;
import ru.panic.withdrawalservice.template.repository.impl.ClientRepositoryImpl;
import ru.panic.withdrawalservice.template.repository.impl.WithdrawalRepositoryImpl;
import ru.panic.withdrawalservice.template.service.WithdrawalService;

import java.util.List;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {
    public WithdrawalServiceImpl(ClientRepositoryImpl clientRepository, WithdrawalRepositoryImpl withdrawalRepository, RabbitTemplate rabbitTemplate, RestTemplate restTemplate) {
        this.clientRepository = clientRepository;
        this.withdrawalRepository = withdrawalRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.restTemplate = restTemplate;
    }

    private final ClientRepositoryImpl clientRepository;
    private final WithdrawalRepositoryImpl withdrawalRepository;
    private final RabbitTemplate rabbitTemplate;
    private final RestTemplate restTemplate;
    @Override
    public CreateWithdrawalResponse createWithdrawal(String jwtToken, CreateWithdrawalRequest request) {
        ResponseEntity<Client> clientResponseEntity = restTemplate.postForEntity(
                "http://localhost:8080/api/auth/getInfoByJwt?jwtToken=" + jwtToken,
                null,
                Client.class
        );
        if (clientResponseEntity.getStatusCode().isError()){
            throw new InvalidCredentialsException("Неверный jwtToken");
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Client client = clientResponseEntity.getBody();

        switch (request.getCurrency()){
            case BTC -> {
                if (client.getBtc_balance() < request.getAmount()){
                    throw new InvalidCredentialsException("Недостаточно средств для вывода");
                }
                if (request.getAmount() < 0.000076){
                    throw new InvalidCredentialsException("Вывести средства на адрес BTC можно только от 0.000076 BTC");
                }
            }
            case ETH -> {
                if (client.getEth_balance() < request.getAmount()){
                    throw new InvalidCredentialsException("Недостаточно средств для вывода");
                }
                if (request.getAmount() < 0.0012){
                    throw new InvalidCredentialsException("Вывести средства на адрес ETH можно только от 0.0012 ETH");
                }
            }
            case LTC -> {
                if (client.getLtc_balance() < request.getAmount()){
                    throw new InvalidCredentialsException("Недостаточно средств для вывода");
                }
                if (request.getAmount() < 0.027){
                    throw new InvalidCredentialsException("Вывести средства на адрес LTC можно только от 0.027 LTC");
                }
            }
            case TON -> {
                if (client.getTon_balance() < request.getAmount()){
                    throw new InvalidCredentialsException("Недостаточно средств для вывода");
                }
                if (request.getAmount() < 0.85){
                    throw new InvalidCredentialsException("Вывести средства на адрес TON можно только от 0.85 TON");
                }
            }
            case TRX -> {
                if (client.getTrx_balance() < request.getAmount()){
                    throw new InvalidCredentialsException("Недостаточно средств для вывода");
                }
                if (request.getAmount() < 15.4){
                    throw new InvalidCredentialsException("Вывести средства на адрес TRX можно только от 15.4 TRX");
                }
            }
            case XRP -> {
                if (client.getXrp_balance() < request.getAmount()){
                    throw new InvalidCredentialsException("Недостаточно средств для вывода");
                }
                if (request.getAmount() < 2.4){
                    throw new InvalidCredentialsException("Вывести средства на адрес XRP можно только от 2.4 XRP");
                }
            }
            case MATIC -> {
                if (client.getMatic_balance() < request.getAmount()){
                    throw new InvalidCredentialsException("Недостаточно средств для вывода");
                }
                if (request.getAmount() < 1.85){
                    throw new InvalidCredentialsException("Вывести средства на адрес MATIC можно только от 1.85 MATIC");
                }
            }
            case TETHER_ERC20 -> {
                if (client.getTetherERC20_balance() < request.getAmount()){
                    throw new InvalidCredentialsException("Недостаточно средств для вывода");
                }
                if (request.getAmount() < 2.3){
                    throw new InvalidCredentialsException("Вывести средства на адрес TETHER ERC-20 можно только от 2.3 TETHER");
                }
            }
        }

        CreateWithdrawalResponse createWithdrawalResponse = new CreateWithdrawalResponse();
        createWithdrawalResponse.setUsername(client.getUsername());
        createWithdrawalResponse.setAmount(request.getAmount());
        createWithdrawalResponse.setCurrency(request.getCurrency());
        createWithdrawalResponse.setTimestamp(System.currentTimeMillis());

        String jsonMessage = null;

        try {
            jsonMessage = objectMapper.writeValueAsString(createWithdrawalResponse);
        }catch (JsonProcessingException ignore){
        }

        rabbitTemplate.convertAndSend("withdrawal-queue", jsonMessage);

        return createWithdrawalResponse;
    }

    @Override
    public List<Withdrawal> readAllWithdrawalByUsername(String jwtToken) {
        ResponseEntity<Client> clientResponseEntity = restTemplate.postForEntity(
                "http://localhost:8080/api/auth/getInfoByJwt?jwtToken=" + jwtToken,
                null,
                Client.class
        );
        if (clientResponseEntity.getStatusCode().isError()){
            throw new InvalidCredentialsException("Неверный jwtToken");
        }

        return withdrawalRepository.findAllWithdrawalByClientUsername(clientResponseEntity.getBody().getUsername());
    }

    @Override
    public void deleteWithdrawal(String jwtToken, Withdrawal withdrawal) {
        ResponseEntity<Client> clientResponseEntity = restTemplate.postForEntity(
                "http://localhost:8080/api/auth/getInfoByJwt?jwtToken=" + jwtToken,
                null,
                Client.class
        );
        if (clientResponseEntity.getStatusCode().isError()){
            throw new InvalidCredentialsException("Неверный jwtToken");
        }

        if (!clientResponseEntity.getBody().getUsername().equals(withdrawal.getClientUsername())){
            throw new InvalidCredentialsException("Вы не можете отменить данный вывод");
        }

        withdrawalRepository.deleteWithdrawalById(withdrawal.getId());
    }
}
