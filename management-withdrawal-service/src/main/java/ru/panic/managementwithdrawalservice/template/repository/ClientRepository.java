package ru.panic.managementwithdrawalservice.template.repository;

import ru.panic.managementwithdrawalservice.template.entity.Client;

public interface ClientRepository {
    Client findClientByUsername(String username);
}
