package ru.panic.managementpaymentservice.template.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.panic.managementpaymentservice.template.dto.PaymentMessage;
import ru.panic.managementpaymentservice.template.dto.WebHookDto;
import ru.panic.managementpaymentservice.template.entity.Company;
import ru.panic.managementpaymentservice.template.entity.Payment;
import ru.panic.managementpaymentservice.template.repository.impl.ClientRepositoryImpl;
import ru.panic.managementpaymentservice.template.repository.impl.CompanyRepositoryImpl;
import ru.panic.managementpaymentservice.template.repository.impl.PaymentRepositoryImpl;
import ru.panic.managementpaymentservice.template.service.PaymentService;
import ru.panic.managementpaymentservice.template.util.WebHookUtil;

@Service
public class PaymentServiceImpl implements PaymentService {
    public PaymentServiceImpl(ClientRepositoryImpl clientRepository, PaymentRepositoryImpl paymentRepository, CompanyRepositoryImpl companyRepository, WebHookUtil webHookUtil) {
        this.clientRepository = clientRepository;
        this.paymentRepository = paymentRepository;
        this.companyRepository = companyRepository;
        this.webHookUtil = webHookUtil;
    }
    private final ClientRepositoryImpl clientRepository;
    private final PaymentRepositoryImpl paymentRepository;
    private final CompanyRepositoryImpl companyRepository;
    private final WebHookUtil webHookUtil;
    @Override
    public void handlePayment(PaymentMessage paymentMessage) {
        Payment payment = new Payment();
        payment.setCompanyName(paymentMessage.getCompanyName());
        payment.setWalletId(paymentMessage.getWalletId());
        payment.setO(paymentMessage.getO());
        payment.setAmount(paymentMessage.getAmount());
        payment.setCurrency(paymentMessage.getCurrency());
        payment.setTimestamp(paymentMessage.getTimestamp());

        paymentRepository.save(payment);

        Company company = companyRepository.findCompanyByCompanyName(payment.getCompanyName());

        String clientUsername = company.getOwner();

        switch (paymentMessage.getCurrency()){
            case BTC -> clientRepository.updateBtcBalanceByUsername(clientUsername,
                    Math.ceil((clientRepository.findBtcBalanceByUsername(clientUsername) + paymentMessage.getAmount()) * 1e7) / 1e7);

            case ETH -> clientRepository.updateEthBalanceByUsername(clientUsername,
                    Math.ceil((clientRepository.findEthBalanceByUsername(clientUsername) + paymentMessage.getAmount()) * 1e6) / 1e6);

            case LTC -> clientRepository.updateLtcBalanceByUsername(clientUsername,
                    Math.ceil((clientRepository.findLtcBalanceByUsername(clientUsername) + paymentMessage.getAmount()) * 1e5) / 1e5);

            case TRX -> clientRepository.updateTrxBalanceByUsername(clientUsername,
                    Math.ceil((clientRepository.findTrxBalanceByUsername(clientUsername) + paymentMessage.getAmount()) * 1e3) / 1e3);

            case TON -> clientRepository.updateTonBalanceByUsername(clientUsername,
                    Math.ceil((clientRepository.findTonBalanceByUsername(clientUsername) + paymentMessage.getAmount()) * 1e3) / 1e3);

            case XRP -> clientRepository.updateXrpBalanceByUsername(clientUsername,
                    Math.ceil((clientRepository.findXrpBalanceByUsername(clientUsername) + paymentMessage.getAmount()) * 1e3) / 1e3);

            case MATIC -> clientRepository.updateMaticBalanceByUsername(clientUsername,
                    Math.ceil((clientRepository.findMaticBalanceByUsername(clientUsername) + paymentMessage.getAmount()) * 1e3) / 1e3);

            case TETHER_ERC20 -> clientRepository.updateTetherERC20BalanceByUsername(clientUsername,
                    Math.ceil((clientRepository.findTetherERC20BalanceByUsername(clientUsername) + paymentMessage.getAmount()) * 1e3) / 1e3);
        }

        webHookUtil.handleWebHook(company.getWebhookUrl(),
                paymentMessage.getO(),
                paymentMessage.getAmount(),
                paymentMessage.getCurrency(),
                paymentMessage.getTimestamp(),
                company.getSecretKey()
        );
    }
}
