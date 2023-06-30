package ru.panic.withdrawalservice.template.repository.impl;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.panic.withdrawalservice.generatedClasses.tables.ClientsTable;
import ru.panic.withdrawalservice.template.entity.Client;
import ru.panic.withdrawalservice.template.repository.ClientRepository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    public ClientRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    private final DSLContext dslContext;
    @Override
    public Client findClientByUsername(String username) {
        return dslContext.selectFrom(ClientsTable.CLIENTS_TABLE)
                .where(ClientsTable.CLIENTS_TABLE.USERNAME.eq(username))
                .fetchOneInto(Client.class);
    }

}
