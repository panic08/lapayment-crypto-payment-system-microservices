package ru.panic.managementpaymentservice.template.repository.impl;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.panic.managementpaymentservice.generatedClasses.tables.ClientsTable;
import ru.panic.managementpaymentservice.template.entity.Client;
import ru.panic.managementpaymentservice.template.repository.ClientRepository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    public ClientRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
    private final DSLContext dslContext;
    @Override
    public void save(Client client) {
        dslContext.insertInto(ClientsTable.CLIENTS_TABLE)
                .set(ClientsTable.CLIENTS_TABLE.USERNAME, client.getUsername())
                .set(ClientsTable.CLIENTS_TABLE.PASSWORD, client.getPassword())
                .set(ClientsTable.CLIENTS_TABLE.EMAIL, client.getEmail())
                .set(ClientsTable.CLIENTS_TABLE.BTC_BALANCE, client.getBtc_balance())
                .set(ClientsTable.CLIENTS_TABLE.ETH_BALANCE, client.getEth_balance())
                .set(ClientsTable.CLIENTS_TABLE.LTC_BALANCE, client.getLtc_balance())
                .set(ClientsTable.CLIENTS_TABLE.TRX_BALANCE, client.getTrx_balance())
                .set(ClientsTable.CLIENTS_TABLE.TON_BALANCE, client.getTon_balance())
                .set(ClientsTable.CLIENTS_TABLE.MATIC_BALANCE, client.getMatic_balance())
                .set(ClientsTable.CLIENTS_TABLE.XRP_BALANCE, client.getXrp_balance())
                .set(ClientsTable.CLIENTS_TABLE.TETHERERC20_BALANCE, client.getTetherERC20_balance())
                .set(ClientsTable.CLIENTS_TABLE.REGISTEREDAT, client.getRegisteredAt())
                .execute();
    }

    @Override
    public void updateBtcBalanceByUsername(String username, double balance) {
        dslContext.update(ClientsTable.CLIENTS_TABLE)
                .set(ClientsTable.CLIENTS_TABLE.BTC_BALANCE, balance)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username)).execute();
    }

    @Override
    public void updateEthBalanceByUsername(String username, double balance) {
        dslContext.update(ClientsTable.CLIENTS_TABLE)
                .set(ClientsTable.CLIENTS_TABLE.ETH_BALANCE, balance)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username)).execute();
    }

    @Override
    public void updateLtcBalanceByUsername(String username, double balance) {
        dslContext.update(ClientsTable.CLIENTS_TABLE)
                .set(ClientsTable.CLIENTS_TABLE.LTC_BALANCE, balance)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username)).execute();
    }

    @Override
    public void updateTonBalanceByUsername(String username, double balance) {
        dslContext.update(ClientsTable.CLIENTS_TABLE)
                .set(ClientsTable.CLIENTS_TABLE.TON_BALANCE, balance)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username)).execute();
    }

    @Override
    public void updateTrxBalanceByUsername(String username, double balance) {
        dslContext.update(ClientsTable.CLIENTS_TABLE)
                .set(ClientsTable.CLIENTS_TABLE.TRX_BALANCE, balance)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username)).execute();
    }

    @Override
    public void updateXrpBalanceByUsername(String username, double balance) {
        dslContext.update(ClientsTable.CLIENTS_TABLE)
                .set(ClientsTable.CLIENTS_TABLE.XRP_BALANCE, balance)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username)).execute();
    }

    @Override
    public void updateMaticBalanceByUsername(String username, double balance) {
        dslContext.update(ClientsTable.CLIENTS_TABLE)
                .set(ClientsTable.CLIENTS_TABLE.MATIC_BALANCE, balance)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username)).execute();
    }

    @Override
    public void updateTetherERC20BalanceByUsername(String username, double balance) {
        dslContext.update(ClientsTable.CLIENTS_TABLE)
                .set(ClientsTable.CLIENTS_TABLE.TETHERERC20_BALANCE, balance)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username)).execute();
    }

    @Override
    public Double findBtcBalanceByUsername(String username) {
        return dslContext.select(ClientsTable.CLIENTS_TABLE.BTC_BALANCE)
                .from(ClientsTable.CLIENTS_TABLE)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username))
                .fetchOneInto(Double.class);
    }

    @Override
    public Double findEthBalanceByUsername(String username) {
        return dslContext.select(ClientsTable.CLIENTS_TABLE.ETH_BALANCE)
                .from(ClientsTable.CLIENTS_TABLE)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username))
                .fetchOneInto(Double.class);
    }

    @Override
    public Double findLtcBalanceByUsername(String username) {
        return dslContext.select(ClientsTable.CLIENTS_TABLE.LTC_BALANCE)
                .from(ClientsTable.CLIENTS_TABLE)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username))
                .fetchOneInto(Double.class);
    }

    @Override
    public Double findTrxBalanceByUsername(String username) {
        return dslContext.select(ClientsTable.CLIENTS_TABLE.TRX_BALANCE)
                .from(ClientsTable.CLIENTS_TABLE)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username))
                .fetchOneInto(Double.class);
    }

    @Override
    public Double findTonBalanceByUsername(String username) {
        return dslContext.select(ClientsTable.CLIENTS_TABLE.TON_BALANCE)
                .from(ClientsTable.CLIENTS_TABLE)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username))
                .fetchOneInto(Double.class);
    }

    @Override
    public Double findXrpBalanceByUsername(String username) {
        return dslContext.select(ClientsTable.CLIENTS_TABLE.XRP_BALANCE)
                .from(ClientsTable.CLIENTS_TABLE)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username))
                .fetchOneInto(Double.class);
    }

    @Override
    public Double findMaticBalanceByUsername(String username) {
        return dslContext.select(ClientsTable.CLIENTS_TABLE.MATIC_BALANCE)
                .from(ClientsTable.CLIENTS_TABLE)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username))
                .fetchOneInto(Double.class);
    }

    @Override
    public Double findTetherERC20BalanceByUsername(String username) {
        return dslContext.select(ClientsTable.CLIENTS_TABLE.TETHERERC20_BALANCE)
                .from(ClientsTable.CLIENTS_TABLE)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username))
                .fetchOneInto(Double.class);
    }

}
