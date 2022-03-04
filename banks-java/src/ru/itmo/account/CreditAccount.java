package ru.itmo.account;

import ru.itmo.bank.Bank;
import ru.itmo.bank.Transaction;
import ru.itmo.client.Client;
import ru.itmo.tools.BanksException;

import java.util.ArrayList;
import java.util.UUID;

public class CreditAccount implements IAccount{
    private UUID id;
    private Bank bank;
    private Client client;
    private double size;
    private double commission;
    private double limit;
    private boolean status;

    public CreditAccount(Bank bank, Client client, double size) {
        id = UUID.randomUUID();
        this.bank = bank;
        this.client = client;
        this.size = size;
        commission = bank.getCommission();
        limit = bank.getLimit();
        status = false;
    }

    /*public UUID getId() {
        return id;
    }*/

    public Transaction withdrawMoney(double money) throws BanksException {
        Transaction newTransaction = new Transaction(id, -money);
        checkAccountStatus(money);
        if (!(size - money > (-1) * limit))
            throw new BanksException("The credit limit reached.");
        size -= money;
        bank.getTransactions().add(newTransaction);
        return newTransaction;
    }

    public Transaction appendMoney(double money) throws BanksException {
        Transaction newTransaction = new Transaction(id, money);
        checkAccountStatus(money);
        size += money;
        bank.getTransactions().add(newTransaction);
        return newTransaction;
    }

    public Transaction transferMoney(UUID accountId, double money) throws BanksException{
        Transaction newTransaction = new Transaction(id, accountId, -money);
        checkAccountStatus(money);
        size -= money;
        IAccount newAccount = bank.getAccountById(accountId);
        newAccount.appendMoney(money);
        bank.getTransactions().add(newTransaction);
        return newTransaction;
    }

    public double updateAccountSize(boolean check) {
        if (size < 0)
            size -= commission;
        return size;
    }

    private void checkAccountStatus(double money) throws BanksException {
        if (!status & money > bank.getMaximumAvailableAmount())
            throw new BanksException("The account is doubtful, the operation is impossible.");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
