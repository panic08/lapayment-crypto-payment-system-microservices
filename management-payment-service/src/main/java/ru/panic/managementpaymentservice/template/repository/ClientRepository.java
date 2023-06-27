package ru.panic.managementpaymentservice.template.repository;

import ru.panic.managementpaymentservice.template.entity.Client;

public interface ClientRepository {
    void save(Client client);
    void updateBtcBalanceByUsername(String username, double balance);
    void updateEthBalanceByUsername(String username, double balance);
    void updateLtcBalanceByUsername(String username, double balance);
    void updateTrxBalanceByUsername(String username, double balance);
    void updateTonBalanceByUsername(String username, double balance);
    void updateXrpBalanceByUsername(String username, double balance);
    void updateMaticBalanceByUsername(String username, double balance);
    void updateTetherERC20BalanceByUsername(String username, double balance);
    Double findBtcBalanceByUsername(String username);
    Double findEthBalanceByUsername(String username);
    Double findLtcBalanceByUsername(String username);
    Double findTrxBalanceByUsername(String username);
    Double findTonBalanceByUsername(String username);
    Double findXrpBalanceByUsername(String username);
    Double findMaticBalanceByUsername(String username);
    Double findTetherERC20BalanceByUsername(String username);
}
