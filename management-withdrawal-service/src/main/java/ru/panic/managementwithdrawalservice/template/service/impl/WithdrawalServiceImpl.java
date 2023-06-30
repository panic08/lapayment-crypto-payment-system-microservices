package ru.panic.managementwithdrawalservice.template.service.impl;

import org.springframework.stereotype.Service;
import ru.panic.managementwithdrawalservice.template.dto.CreateWithdrawalMessage;
import ru.panic.managementwithdrawalservice.template.entity.Client;
import ru.panic.managementwithdrawalservice.template.entity.Withdrawal;
import ru.panic.managementwithdrawalservice.template.repository.impl.ClientRepositoryImpl;
import ru.panic.managementwithdrawalservice.template.repository.impl.WithdrawalRepositoryImpl;
import ru.panic.managementwithdrawalservice.template.service.WithdrawalService;
@Service
public class WithdrawalServiceImpl implements WithdrawalService {
    public WithdrawalServiceImpl(ClientRepositoryImpl clientRepository, WithdrawalRepositoryImpl withdrawalRepository) {
        this.clientRepository = clientRepository;
        this.withdrawalRepository = withdrawalRepository;
    }

    private final ClientRepositoryImpl clientRepository;
    private final WithdrawalRepositoryImpl withdrawalRepository;
    @Override
    public void handleWithdrawal(CreateWithdrawalMessage createWithdrawalMessage) {
        Client client = clientRepository.findClientByUsername(createWithdrawalMessage.getUsername());
        switch (createWithdrawalMessage.getCurrency()){
            case BTC -> {
                if (client.getBtc_balance() < createWithdrawalMessage.getAmount()){
                    return;
                }
            }
            case ETH -> {
                if (client.getEth_balance() < createWithdrawalMessage.getAmount()){
                    return;
                }
            }
            case LTC -> {
                if (client.getLtc_balance() < createWithdrawalMessage.getAmount()){
                    return;
                }
            }
            case TON -> {
                if (client.getTon_balance() < createWithdrawalMessage.getAmount()){
                    return;
                }
            }
            case TRX -> {
                if (client.getTrx_balance() < createWithdrawalMessage.getAmount()){
                    return;
                }
            }
            case XRP -> {
                if (client.getXrp_balance() < createWithdrawalMessage.getAmount()){
                    return;
                }
            }
            case MATIC -> {
                if (client.getMatic_balance() < createWithdrawalMessage.getAmount()){
                    return;
                }
            }
            case TETHER_ERC20 -> {
                if (client.getTetherERC20_balance() < createWithdrawalMessage.getAmount()){
                    return;
                }
            }
        }

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setClientUsername(client.getUsername());
        withdrawal.setAmount(createWithdrawalMessage.getAmount());
        withdrawal.setCurrency(createWithdrawalMessage.getCurrency());
        withdrawal.setTimestamp(createWithdrawalMessage.getTimestamp());

        withdrawalRepository.save(withdrawal);
    }
}
