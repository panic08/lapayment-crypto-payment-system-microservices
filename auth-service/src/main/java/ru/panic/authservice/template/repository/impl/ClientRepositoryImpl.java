package ru.panic.authservice.template.repository.impl;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.panic.authservice.generatedClasses.tables.ClientsTable;
import ru.panic.authservice.template.entity.Client;

@Repository
public class ClientRepositoryImpl implements ru.panic.authservice.template.repository.ClientRepository {
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
                .execute();
    }

    @Override
    public Client findByUsername(String username) {
        Client client = dslContext.selectFrom(ClientsTable.CLIENTS_TABLE)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username))
                .fetchOneInto(Client.class);
        return client != null ? client : null;
    }

    @Override
    public boolean extendedByPassword(String password) {

        return false;
    }
}
