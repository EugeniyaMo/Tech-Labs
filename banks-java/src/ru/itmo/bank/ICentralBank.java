package ru.itmo.bank;

import ru.itmo.account.IAccount;
import ru.itmo.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface ICentralBank {
    List<Bank> getBanks();

    List<Client> getClients();

    Bank registerNewBank(Bank bank);

    Client addNewClientToBank(Client client, Bank bank);

    IAccount createNewAccount(Client client, Bank bank, TypeAccount typeAccount, double accountSize);

    void notifyUpdateAccountSize(Bank bank);

    void canselTransaction(Bank bank, UUID transactionID);
}
