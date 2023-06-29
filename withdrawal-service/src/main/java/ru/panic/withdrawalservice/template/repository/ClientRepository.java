package ru.panic.withdrawalservice.template.repository;

import ru.panic.withdrawalservice.template.entity.Client;

public interface ClientRepository {
    Client findClientByUsername(String username);
}
