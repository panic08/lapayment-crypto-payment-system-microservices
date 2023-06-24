package ru.panic.authservice.template.repository;

import ru.panic.authservice.template.entity.Client;

public interface ClientRepository {
    void save(Client client);
    Client findByUsername(String username);
    boolean extendedByPassword(String password);
}
