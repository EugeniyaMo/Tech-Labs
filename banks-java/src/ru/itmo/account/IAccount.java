package ru.itmo.account;

import ru.itmo.bank.Transaction;
import ru.itmo.client.Client;
import ru.itmo.tools.BanksException;

import java.util.UUID;

public interface IAccount {
    UUID getId();
    Client getClient();
    double getSize();
    Transaction withdrawMoney(double money) throws BanksException;
    Transaction appendMoney(double money) throws BanksException;
    Transaction transferMoney(UUID accountId, double money) throws BanksException;
    double updateAccountSize(boolean check);
}
