package ru.panic.paymentservice.template.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.panic.paymentservice.template.bunny.NetworkBunny;
import ru.panic.paymentservice.template.dto.PaymentMessage;
import ru.panic.paymentservice.template.dto.PaymentRequest;
import ru.panic.paymentservice.template.dto.PaymentResponse;
import ru.panic.paymentservice.template.dto.crypto.*;
import ru.panic.paymentservice.template.dto.crypto.xrp.XrpAccountTxRequest;
import ru.panic.paymentservice.template.enums.CryptoCurrency;
import ru.panic.paymentservice.template.exception.AmountBelowMinimumException;
import ru.panic.paymentservice.template.service.PaymentService;

import java.util.Timer;
import java.util.TimerTask;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    public PaymentServiceImpl(RestTemplate restTemplate, NetworkBunny networkBunny, RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.networkBunny = networkBunny;
        this.rabbitTemplate = rabbitTemplate;
    }

    private final RestTemplate restTemplate;
    private final NetworkBunny networkBunny;
    private final RabbitTemplate rabbitTemplate;
    private static final String COIN_PROVIDER_URL =
            "https://api.coingecko.com/api/v3/simple/price?ids=tron,bitcoin,ethereum,litecoin,ripple,matic-network,tether,the-open-network&vs_currencies=rub,eur,usd,pln";
    @Override
    public PaymentResponse handlePaymentByBitcoin(PaymentRequest request) {

        switch (request.getCurrency()){
            case RUB -> {
                if (request.getAmount() < 200){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения BTC - 200 RUB");
                }
            }

            case EUR -> {
                if (request.getAmount() < 2){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения BTC - 2 EUR");
                }
            }

            case USD -> {
                if (request.getAmount() < 2){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения BTC - 2 USD");
                }
            }

            case PLN -> {
                if (request.getAmount() < 9.5){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения BTC - 9.5 PLN");
                }
            }
        }

        ResponseEntity<CoinProviderResponse> coinProviderResponseResponseEntity =
                restTemplate.getForEntity(COIN_PROVIDER_URL, CoinProviderResponse.class);

        CoinProviderResponse coinProviderResponse = coinProviderResponseResponseEntity.getBody();

        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setWalletId(networkBunny.getBtcWallet());

        switch (request.getCurrency()){
            case RUB -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getBitcoin().getRub()) * 1e7) / 1e7);
            case USD -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getBitcoin().getUsd()) * 1e7) / 1e7);
            case EUR -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getBitcoin().getEur()) * 1e7) / 1e7);
            case PLN -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getBitcoin().getPln()) * 1e7) / 1e7);
        }

        paymentResponse.setCurrency(CryptoCurrency.BTC);
        paymentResponse.setIpAddress(request.getIpAddress());
        paymentResponse.setTimestamp(System.currentTimeMillis());

        new Thread(() -> {
            long currentTime = System.currentTimeMillis();
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                int counter = 0;
                public void run() {
                    ResponseEntity<BitcoinProviderResponse> bitcoinResponseDto = restTemplate
                            .getForEntity("https://blockchain.info/rawaddr/" + networkBunny.getBtcWallet() + "?limit=5", BitcoinProviderResponse.class);

                    for (BitcoinProviderResponse.TransactionDTO tx : bitcoinResponseDto.getBody().getTxs()) {
                        for (BitcoinProviderResponse.InputDTO input : tx.getInputs()) {
                            if(input.getPrev_out().getValue()/1e8 == paymentResponse.getAmount()
                                    && tx.getTime() > currentTime
                            ){

                                PaymentMessage paymentMessage = new PaymentMessage();
                                paymentMessage.setCompanyName(request.getCompanyName());
                                paymentMessage.setAmount(paymentResponse.getAmount());
                                paymentMessage.setCurrency(CryptoCurrency.BTC);
                                paymentMessage.setWalletId(input.getPrev_out().getAddr());
                                paymentMessage.setIpAddress(request.getIpAddress());
                                paymentMessage.setO(request.getO());
                                paymentMessage.setTimestamp(System.currentTimeMillis());

                                ObjectMapper objectMapper = new ObjectMapper();
                                String jsonString;
                                try {
                                    jsonString = objectMapper.writeValueAsString(paymentMessage);
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }
                                rabbitTemplate.convertAndSend("payment-queue", jsonString);
                                return;
                            }
                        }
                    }

                    counter++;
                    if (counter == 12 * 4) {
                        timer.cancel();
                    }
                }
            };

            long delay = 0;
            long period = 4000;

            timer.scheduleAtFixedRate(task, delay, period);

        }).start();

        return paymentResponse;
    }

    @Override
    public PaymentResponse handlePaymentByEthereum(PaymentRequest request) {

        switch (request.getCurrency()){
            case RUB -> {
                if (request.getAmount() < 200){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения ETH - 200 RUB");
                }
            }

            case EUR -> {
                if (request.getAmount() < 2){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения ETH - 2 EUR");
                }
            }

            case USD -> {
                if (request.getAmount() < 2){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения ETH - 2 USD");
                }
            }

            case PLN -> {
                if (request.getAmount() < 9.5){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения ETH - 9.5 PLN");
                }
            }
        }

        ResponseEntity<CoinProviderResponse> coinProviderResponseResponseEntity =
                restTemplate.getForEntity(COIN_PROVIDER_URL, CoinProviderResponse.class);

        CoinProviderResponse coinProviderResponse = coinProviderResponseResponseEntity.getBody();

        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setWalletId(networkBunny.getEthWallet());

        switch (request.getCurrency()){
            case RUB -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getEthereum().getRub()) * 1e6) / 1e6);
            case USD -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getEthereum().getUsd()) * 1e6) / 1e6);
            case EUR -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getEthereum().getEur()) * 1e6) / 1e6);
            case PLN -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getEthereum().getPln()) * 1e6) / 1e6);
        }

        paymentResponse.setCurrency(CryptoCurrency.ETH);
        paymentResponse.setIpAddress(request.getIpAddress());
        paymentResponse.setTimestamp(System.currentTimeMillis());

        new Thread(() -> {

            Timer timer = new Timer();
            long currentTime = System.currentTimeMillis();
            TimerTask task = new TimerTask() {
                int counter = 0;
                public void run() {
                    ResponseEntity<EthereumProviderResponse> ethereumResponseDto = restTemplate
                            .getForEntity("https://api.etherscan.io/api?module=account&action=txlist&address="
                                    + networkBunny.getEthWallet() + "&startblock=0&endblock=99999999&sort=desc&apikey="
                                    + networkBunny.getEthApiToken() + "&offset=0&limit=5", EthereumProviderResponse.class);

                    for (EthereumProviderResponse.TransactionDto transactionDto : ethereumResponseDto.getBody().getResult()) {
                        if ((double) Long.parseLong(transactionDto.getValue())/1e18 == paymentResponse.getAmount()
                                &&
                                Long.parseLong(transactionDto.getTimeStamp()) > currentTime
                        ){

                            PaymentMessage paymentMessage = new PaymentMessage();
                            paymentMessage.setCompanyName(request.getCompanyName());
                            paymentMessage.setAmount(paymentResponse.getAmount());
                            paymentMessage.setCurrency(CryptoCurrency.ETH);
                            paymentMessage.setWalletId(transactionDto.getFrom());
                            paymentMessage.setIpAddress(request.getIpAddress());
                            paymentMessage.setO(request.getO());
                            paymentMessage.setTimestamp(System.currentTimeMillis());

                            ObjectMapper objectMapper = new ObjectMapper();
                            String jsonString;
                            try {
                                jsonString = objectMapper.writeValueAsString(paymentMessage);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                            rabbitTemplate.convertAndSend("payment-queue", jsonString);
                            return;
                        }
                    }

                    counter++;
                    if (counter == 12 * 4) {
                        timer.cancel();
                    }
                }
            };
            long delay = 0;
            long period = 4000;

            timer.scheduleAtFixedRate(task, delay, period);

        }).start();

        return paymentResponse;
    }

    @Override
    public PaymentResponse handlePaymentByLitecoin(PaymentRequest request) {
        switch (request.getCurrency()){
            case RUB -> {
                if (request.getAmount() < 200){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения LTC - 200 RUB");
                }
            }

            case EUR -> {
                if (request.getAmount() < 2){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения LTC - 2 EUR");
                }
            }

            case USD -> {
                if (request.getAmount() < 2){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения LTC - 2 USD");
                }
            }

            case PLN -> {
                if (request.getAmount() < 9.5){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения LTC - 9.5 PLN");
                }
            }
        }

        ResponseEntity<CoinProviderResponse> coinProviderResponseResponseEntity =
                restTemplate.getForEntity(COIN_PROVIDER_URL, CoinProviderResponse.class);

        CoinProviderResponse coinProviderResponse = coinProviderResponseResponseEntity.getBody();

        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setWalletId(networkBunny.getLtcWallet());

        switch (request.getCurrency()){
            case RUB -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getLitecoin().getRub()) * 1e5) / 1e5);
            case USD -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getLitecoin().getUsd()) * 1e5) / 1e5);
            case EUR -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getLitecoin().getEur()) * 1e5) / 1e5);
            case PLN -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getLitecoin().getPln()) * 1e5) / 1e5);
        }

        paymentResponse.setCurrency(CryptoCurrency.LTC);
        paymentResponse.setIpAddress(request.getIpAddress());
        paymentResponse.setTimestamp(System.currentTimeMillis());

        new Thread(() -> {
            Timer timer = new Timer();
            long currentTime = System.currentTimeMillis();
            TimerTask task = new TimerTask() {
                int counter = 0;
                public void run() {
                    ResponseEntity<LitecoinProviderResponse> liteCoinResponseDto = restTemplate
                            .getForEntity("https://api.tatum.io/v3/litecoin/transaction/address/"
                                    + networkBunny.getLtcWallet() + "?pageSize=5&sort=desc", LitecoinProviderResponse.class);

                    for (LitecoinProviderResponse.ReplenishmentDto replenishment : liteCoinResponseDto.getBody().getReplenishments()) {
                        for (LitecoinProviderResponse.ReplenishmentDto.InputDto input : replenishment.getInputs()) {
                            if(Double.parseDouble(input.getCoin().getValue()) == paymentResponse.getAmount() && replenishment.getTime() > currentTime){

                                PaymentMessage paymentMessage = new PaymentMessage();
                                paymentMessage.setCompanyName(request.getCompanyName());
                                paymentMessage.setAmount(paymentResponse.getAmount());
                                paymentMessage.setCurrency(CryptoCurrency.LTC);
                                paymentMessage.setWalletId(input.getCoin().getAddress());
                                paymentMessage.setIpAddress(request.getIpAddress());
                                paymentMessage.setO(request.getO());
                                paymentMessage.setTimestamp(System.currentTimeMillis());

                                ObjectMapper objectMapper = new ObjectMapper();
                                String jsonString;
                                try {
                                    jsonString = objectMapper.writeValueAsString(paymentMessage);
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }
                                rabbitTemplate.convertAndSend("payment-queue", jsonString);
                                return;
                            }
                        }
                    }

                    counter++;

                    if (counter == 12 * 4) {
                        timer.cancel();
                    }
                }
            };

            long delay = 0;
            long period = 4000;

            timer.scheduleAtFixedRate(task, delay, period);

        }).start();

        return paymentResponse;
    }

    @Override
    public PaymentResponse handlePaymentByTron(PaymentRequest request) {
        switch (request.getCurrency()){
            case RUB -> {
                if (request.getAmount() < 100){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TRX - 100 RUB");
                }
            }

            case EUR -> {
                if (request.getAmount() < 1){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TRX - 1 EUR");
                }
            }

            case USD -> {
                if (request.getAmount() < 1){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TRX - 1 USD");
                }
            }

            case PLN -> {
                if (request.getAmount() < 5){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TRX - 5 PLN");
                }
            }
        }

        ResponseEntity<CoinProviderResponse> coinProviderResponseResponseEntity =
                restTemplate.getForEntity(COIN_PROVIDER_URL, CoinProviderResponse.class);

        CoinProviderResponse coinProviderResponse = coinProviderResponseResponseEntity.getBody();

        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setWalletId(networkBunny.getTrxWallet());

        switch (request.getCurrency()){
            case RUB -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTron().getRub()) * 1e3) / 1e3);
            case USD -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTron().getUsd()) * 1e3) / 1e3);
            case EUR -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTron().getEur()) * 1e3) / 1e3);
            case PLN -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTron().getPln()) * 1e3) / 1e3);
        }

        paymentResponse.setCurrency(CryptoCurrency.TRX);
        paymentResponse.setIpAddress(request.getIpAddress());
        paymentResponse.setTimestamp(System.currentTimeMillis());

        new Thread(() -> {

            Timer timer = new Timer();
            long currentTime = System.currentTimeMillis();
            TimerTask task = new TimerTask() {
                int counter = 0;
                public void run() {
                    ResponseEntity<TronProviderResponse> tronResponseDto = restTemplate
                            .getForEntity("https://api.trongrid.io/v1/accounts/" + networkBunny.getTrxWallet() + "/transactions?limit=5", TronProviderResponse.class);

                    for (TronProviderResponse.Data datum : tronResponseDto.getBody().getData()) {
                        for (TronProviderResponse.Contract contract : datum.getRaw_data().getContract()) {
                            if (contract.getParameter().getValue().getAmount()/1e6 == paymentResponse.getAmount() &&
                                    datum.getRaw_data().getTimestamp() > currentTime){

                                PaymentMessage paymentMessage = new PaymentMessage();
                                paymentMessage.setCompanyName(request.getCompanyName());
                                paymentMessage.setAmount(paymentResponse.getAmount());
                                paymentMessage.setCurrency(CryptoCurrency.TRX);
                                paymentMessage.setWalletId(contract.getParameter().getValue().getOwner_address());
                                paymentMessage.setIpAddress(request.getIpAddress());
                                paymentMessage.setO(request.getO());
                                paymentMessage.setTimestamp(System.currentTimeMillis());

                                ObjectMapper objectMapper = new ObjectMapper();
                                String jsonString;
                                try {
                                    jsonString = objectMapper.writeValueAsString(paymentMessage);
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }
                                rabbitTemplate.convertAndSend("payment -queue", jsonString);
                                return;
                            }
                        }
                    }

                    counter++;

                    if (counter == 12 * 4) {
                        timer.cancel();
                    }
                }
            };

            long delay = 0;
            long period = 4000;

            timer.scheduleAtFixedRate(task, delay, period);
        }).start();

        return paymentResponse;
    }

    @Override
    public PaymentResponse handlePaymentByTon(PaymentRequest request) {
        switch (request.getCurrency()){
            case RUB -> {
                if (request.getAmount() < 100){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TON - 100 RUB");
                }
            }

            case EUR -> {
                if (request.getAmount() < 1){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TON - 1 EUR");
                }
            }

            case USD -> {
                if (request.getAmount() < 1){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TON - 1 USD");
                }
            }

            case PLN -> {
                if (request.getAmount() < 5){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TON - 5 PLN");
                }
            }
        }

        ResponseEntity<CoinProviderResponse> coinProviderResponseResponseEntity =
                restTemplate.getForEntity(COIN_PROVIDER_URL, CoinProviderResponse.class);

        CoinProviderResponse coinProviderResponse = coinProviderResponseResponseEntity.getBody();

        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setWalletId(networkBunny.getTonWallet());

        switch (request.getCurrency()){
            case RUB -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTheOpenNetwork().getRub()) * 1e3) / 1e3);
            case USD -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTheOpenNetwork().getUsd()) * 1e3) / 1e3);
            case EUR -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTheOpenNetwork().getEur()) * 1e3) / 1e3);
            case PLN -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTheOpenNetwork().getPln()) * 1e3) / 1e3);
        }

        paymentResponse.setCurrency(CryptoCurrency.TON);
        paymentResponse.setIpAddress(request.getIpAddress());
        paymentResponse.setTimestamp(System.currentTimeMillis());

        new Thread(() -> {
            Timer timer = new Timer();
            long currentTime = System.currentTimeMillis();
            TimerTask task = new TimerTask() {
                int counter = 0;
                public void run() {

                    ResponseEntity<TonProviderResponse> tonResponseDto = restTemplate
                            .exchange(
                                    "https://stage.toncenter.com/api/v2/getTransactions?address=" + networkBunny.getTonWallet() + "&limit=5&to_lt=0&archival=false",
                                    HttpMethod.GET,
                                    null,
                                    TonProviderResponse.class
                            );
                    for (TonProviderResponse.ResultDTO resultDTO : tonResponseDto.getBody().getResult()) {
                        if((double) Long.parseLong(resultDTO.getIn_msg().getValue())/1e9
                                ==
                                paymentResponse.getAmount()
                                &&
                                resultDTO.getUtime() > currentTime
                        ){

                            PaymentMessage paymentMessage = new PaymentMessage();
                            paymentMessage.setCompanyName(request.getCompanyName());
                            paymentMessage.setAmount(paymentResponse.getAmount());
                            paymentMessage.setCurrency(CryptoCurrency.TON);
                            paymentMessage.setWalletId(resultDTO.getIn_msg().getSource());
                            paymentMessage.setIpAddress(request.getIpAddress());
                            paymentMessage.setO(request.getO());
                            paymentMessage.setTimestamp(System.currentTimeMillis());

                            ObjectMapper objectMapper = new ObjectMapper();
                            String jsonString;
                            try {
                                jsonString = objectMapper.writeValueAsString(paymentMessage);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                            rabbitTemplate.convertAndSend("payment-queue", jsonString);
                            return;
                        }
                    }

                    counter++;

                    if (counter == 12 * 4) {
                        timer.cancel();
                    }
                }
            };

            long delay = 0;
            long period = 4000;

            timer.scheduleAtFixedRate(task, delay, period);
        }).start();

        return paymentResponse;
    }

    @Override
    public PaymentResponse handlePaymentByMatic(PaymentRequest request) {
        switch (request.getCurrency()){
            case RUB -> {
                if (request.getAmount() < 100){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения MATIC - 100 RUB");
                }
            }

            case EUR -> {
                if (request.getAmount() < 1){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения MATIC - 1 EUR");
                }
            }

            case USD -> {
                if (request.getAmount() < 1){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения MATIC - 1 USD");
                }
            }

            case PLN -> {
                if (request.getAmount() < 5){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения MATIC - 5 PLN");
                }
            }
        }

        ResponseEntity<CoinProviderResponse> coinProviderResponseResponseEntity =
                restTemplate.getForEntity(COIN_PROVIDER_URL, CoinProviderResponse.class);

        CoinProviderResponse coinProviderResponse = coinProviderResponseResponseEntity.getBody();

        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setWalletId(networkBunny.getMaticWallet());

        switch (request.getCurrency()){
            case RUB -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getMaticNetwork().getRub()) * 1e3) / 1e3);
            case USD -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getMaticNetwork().getUsd()) * 1e3) / 1e3);
            case EUR -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getMaticNetwork().getEur()) * 1e3) / 1e3);
            case PLN -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getMaticNetwork().getPln()) * 1e3) / 1e3);
        }

        paymentResponse.setCurrency(CryptoCurrency.MATIC);
        paymentResponse.setIpAddress(request.getIpAddress());
        paymentResponse.setTimestamp(System.currentTimeMillis());

        new Thread(() -> {
            Timer timer = new Timer();
            long currentTime = System.currentTimeMillis();
            TimerTask task = new TimerTask() {
                int counter = 0;
                public void run() {
                    ResponseEntity<MaticProviderResponse> maticResponseDto = restTemplate
                            .getForEntity("https://api.tatum.io/v3/polygon/account/transaction/"
                                    + networkBunny.getMaticWallet() + "?pageSize=5&sort=desc", MaticProviderResponse.class);

                    for (MaticProviderResponse.ResponseDto responseDto : maticResponseDto.getBody().getResponseDtos()) {
                        if ((double) Long.parseLong(responseDto.getValue())/1e18 == paymentResponse.getAmount()
                                &&
                                responseDto.getTimestamp() > currentTime
                        ){

                            PaymentMessage paymentMessage = new PaymentMessage();
                            paymentMessage.setCompanyName(request.getCompanyName());
                            paymentMessage.setAmount(paymentResponse.getAmount());
                            paymentMessage.setCurrency(CryptoCurrency.MATIC);
                            paymentMessage.setWalletId(responseDto.getFrom());
                            paymentMessage.setIpAddress(request.getIpAddress());
                            paymentMessage.setO(request.getO());
                            paymentMessage.setTimestamp(System.currentTimeMillis());

                            ObjectMapper objectMapper = new ObjectMapper();
                            String jsonString;
                            try {
                                jsonString = objectMapper.writeValueAsString(paymentMessage);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                            rabbitTemplate.convertAndSend("payment-queue", jsonString);
                            return;
                        }
                    }

                    counter++;
                    if (counter == 12 * 4) {
                        timer.cancel();
                    }
                }
            };

            long delay = 0;
            long period = 4000;

            timer.scheduleAtFixedRate(task, delay, period);
        }).start();

        return paymentResponse;
    }

    @Override
    public PaymentResponse handlePaymentByRipple(PaymentRequest request) {
        switch (request.getCurrency()){
            case RUB -> {
                if (request.getAmount() < 100){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения XRP - 100 RUB");
                }
            }

            case EUR -> {
                if (request.getAmount() < 1){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения XRP - 1 EUR");
                }
            }

            case USD -> {
                if (request.getAmount() < 1){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения XRP - 1 USD");
                }
            }

            case PLN -> {
                if (request.getAmount() < 5){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения XRP- 5 PLN");
                }
            }
        }

        ResponseEntity<CoinProviderResponse> coinProviderResponseResponseEntity =
                restTemplate.getForEntity(COIN_PROVIDER_URL, CoinProviderResponse.class);

        CoinProviderResponse coinProviderResponse = coinProviderResponseResponseEntity.getBody();

        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setWalletId(networkBunny.getXrpWallet());

        switch (request.getCurrency()){
            case RUB -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getRipple().getRub()) * 1e3) / 1e3);
            case USD -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getRipple().getUsd()) * 1e3) / 1e3);
            case EUR -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getRipple().getEur()) * 1e3) / 1e3);
            case PLN -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getRipple().getPln()) * 1e3) / 1e3);
        }

        paymentResponse.setCurrency(CryptoCurrency.XRP);
        paymentResponse.setIpAddress(request.getIpAddress());
        paymentResponse.setTimestamp(System.currentTimeMillis());

        new Thread(() -> {
            Timer timer = new Timer();
            long currentTime = System.currentTimeMillis();
            TimerTask task = new TimerTask() {
                int counter = 0;
                XrpAccountTxRequest request1 = new XrpAccountTxRequest(networkBunny.getXrpWallet(), 5);
                HttpHeaders headers = new HttpHeaders();
                HttpEntity<XrpAccountTxRequest> entity = new HttpEntity<>(request1, headers);
                public void run() {

                    ResponseEntity<RippleProviderResponse> xrpResponseDto = restTemplate
                            .exchange(
                                    "https://s1.ripple.com:51234/",
                                    HttpMethod.POST,
                                    entity,
                                    RippleProviderResponse.class
                            );

                    for (RippleProviderResponse.Transaction transaction : xrpResponseDto.getBody().getResult().getTransactions()) {
                        if(Double.parseDouble(transaction.getTx().getAmount()) == paymentResponse.getAmount()
                                &&
                                transaction.getTx().getDate()+946684800L > currentTime
                        ){

                            PaymentMessage paymentMessage = new PaymentMessage();
                            paymentMessage.setCompanyName(request.getCompanyName());
                            paymentMessage.setAmount(paymentResponse.getAmount());
                            paymentMessage.setCurrency(CryptoCurrency.XRP);
                            paymentMessage.setWalletId(transaction.getTx().getAccount());
                            paymentMessage.setIpAddress(request.getIpAddress());
                            paymentMessage.setO(request.getO());
                            paymentMessage.setTimestamp(System.currentTimeMillis());


                            ObjectMapper objectMapper = new ObjectMapper();
                            String jsonString;
                            try {
                                jsonString = objectMapper.writeValueAsString(paymentMessage);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                            rabbitTemplate.convertAndSend("payment-queue", jsonString);
                            return;
                        }
                    }

                    counter++;

                    if (counter == 12 * 4) {
                        timer.cancel();
                    }
                }
            };

            long delay = 0;
            long period = 4000;

            timer.scheduleAtFixedRate(task, delay, period);
        }).start();

        return paymentResponse;
    }

    @Override
    public PaymentResponse handlePaymentByTetherERC20(PaymentRequest request) {

        switch (request.getCurrency()){
            case RUB -> {
                if (request.getAmount() < 200){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TETHER-ERC20 - 200 RUB");
                }
            }

            case EUR -> {
                if (request.getAmount() < 2){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TETHER-ERC20 - 2 EUR");
                }
            }

            case USD -> {
                if (request.getAmount() < 2){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TETHER-ERC20 - 2 USD");
                }
            }

            case PLN -> {
                if (request.getAmount() < 9.5){
                    throw new AmountBelowMinimumException("Минимальная сумма для пополнения TETHER-ERC20 - 9.5 PLN");
                }
            }
        }

        ResponseEntity<CoinProviderResponse> coinProviderResponseResponseEntity =
                restTemplate.getForEntity(COIN_PROVIDER_URL, CoinProviderResponse.class);

        CoinProviderResponse coinProviderResponse = coinProviderResponseResponseEntity.getBody();

        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setWalletId(networkBunny.getTetherERC20Wallet());

        switch (request.getCurrency()){
            case RUB -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTether().getRub()) * 1e3) / 1e3);
            case USD -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTether().getUsd()) * 1e3) / 1e3);
            case EUR -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTether().getEur()) * 1e3) / 1e3);
            case PLN -> paymentResponse.setAmount(Math.ceil((request.getAmount() / coinProviderResponse.getTether().getPln()) * 1e3) / 1e3);
        }

        paymentResponse.setCurrency(CryptoCurrency.TETHER_ERC20);
        paymentResponse.setIpAddress(request.getIpAddress());
        paymentResponse.setTimestamp(System.currentTimeMillis());

        new Thread(() -> {
            Timer timer = new Timer();
            long currentTime = System.currentTimeMillis();
            TimerTask task = new TimerTask() {
                int counter = 0;
                public void run() {
                    ResponseEntity<TetherERC20ProviderResponse> tetherERC20ResponseDto = restTemplate
                            .getForEntity("https://api.etherscan.io/api?module=account&action=tokentx&contractaddress=0xdac17f958d2ee523a2206206994597c13d831ec7&"
                                    + networkBunny.getEthWallet() + "&" + networkBunny.getEthApiToken() + "&page=1&offset=5&sort=desc",
                                    TetherERC20ProviderResponse.class);

                    for (TetherERC20ProviderResponse.ResultDTO resultDTO : tetherERC20ResponseDto.getBody().getResult()) {
                        if(Double.parseDouble(resultDTO.getValue())/(10^Integer.parseInt(resultDTO.getTokenDecimal())) == paymentResponse.getAmount()
                                &&
                                Long.parseLong(resultDTO.getTimeStamp()) > currentTime
                        ){

                            PaymentMessage paymentMessage = new PaymentMessage();
                            paymentMessage.setCompanyName(request.getCompanyName());
                            paymentMessage.setAmount(paymentResponse.getAmount());
                            paymentMessage.setCurrency(CryptoCurrency.TETHER_ERC20);
                            paymentMessage.setWalletId(resultDTO.getFrom());
                            paymentMessage.setIpAddress(request.getIpAddress());
                            paymentMessage.setO(request.getO());
                            paymentMessage.setTimestamp(System.currentTimeMillis());

                            ObjectMapper objectMapper = new ObjectMapper();
                            String jsonString;
                            try {
                                jsonString = objectMapper.writeValueAsString(paymentMessage);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                            rabbitTemplate.convertAndSend("payment-queue", jsonString);
                            return;
                        }
                    }

                    counter++;

                    if (counter == 12 * 4) {
                        timer.cancel();
                    }
                }
            };

            long delay = 0;
            long period = 4000;

            timer.scheduleAtFixedRate(task, delay, period);
        }).start();

        return paymentResponse;
    }
}
