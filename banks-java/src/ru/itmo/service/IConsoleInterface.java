package ru.itmo.service;

import ru.itmo.client.Client;
import ru.itmo.tools.BanksException;

public interface IConsoleInterface {
    Client greeting();
    Client registration();
    void workingWithAccount(Client client) throws BanksException;
    void createNewAccount(Client client);
}
