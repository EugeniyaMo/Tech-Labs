package ru.itmo.account;

import ru.itmo.bank.Bank;
import ru.itmo.bank.Transaction;
import ru.itmo.client.Client;
import ru.itmo.tools.BanksException;

import java.util.ArrayList;
import java.util.UUID;

public class DebitAccount implements IAccount {
    private UUID id;
    private Bank bank;
    private Client client;
    private double size;
    private double percent;
    private double accumulatedSum;
    private boolean status;

    public DebitAccount(Bank bank, Client client, double size) {
        id = UUID.randomUUID();
        this.bank = bank;
        this.client = client;
        this.size = size;
        percent = bank.getPercent();
        accumulatedSum = 0;
        status = false;
    }

    public Transaction withdrawMoney(double money) throws BanksException {
        Transaction newTransaction = new Transaction(id, -money);
        checkAccountStatus(money);
        if (size < money)
            throw new BanksException("Insufficient money in the account.");
        size -= money;
        bank.getTransactions().add(newTransaction);
        return newTransaction;
    }

    public Transaction appendMoney(double money) {
        Transaction newTransaction = new Transaction(id, money);
        size += money;
        bank.getTransactions().add(newTransaction);
        return newTransaction;
    }

    public Transaction transferMoney(UUID accountId, double money) throws BanksException {
        Transaction newTransaction = new Transaction(id, accountId, -money);
        checkAccountStatus(money);
        if (size < money)
            throw new BanksException("Not enough money in the account.");
        size -= money;
        IAccount newAccount = bank.getAccountById(accountId);
        newAccount.appendMoney(money);
        bank.getTransactions().add(newTransaction);
        return newTransaction;
    }

    public double updateAccountSize(boolean check) {
        if (check) {
            size += accumulatedSum;
            accumulatedSum = 0;
        } else {
            accumulatedSum += size * (percent / 100 / 365);
        }
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

    @Override
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getAccumulatedSum() {
        return accumulatedSum;
    }

    public void setAccumulatedSum(double accumulatedSum) {
        this.accumulatedSum = accumulatedSum;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

