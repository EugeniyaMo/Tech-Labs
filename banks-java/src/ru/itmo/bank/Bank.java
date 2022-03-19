package ru.itmo.bank;

import ru.itmo.account.IAccount;
import ru.itmo.client.Client;
import ru.itmo.tools.BanksException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private String name;
    private double percent;
    private double initialPercent;
    private double commission;
    private double maximumAvailableAmount;
    private double limit;
    private List<IAccount> accounts;
    private List<Client> clients;
    private List<Transaction> transactions;

    public Bank(String name, double percent, double initialPercent, double commision,
                double maximumAvailableAmount, double limit) {
        this.name = name;
        this.percent = percent;
        this.initialPercent = initialPercent;
        this.commission = commision;
        this.maximumAvailableAmount = maximumAvailableAmount;
        this.limit = limit;
        accounts = new ArrayList<IAccount>();
        clients = new ArrayList<Client>();
        transactions = new ArrayList<Transaction>();
    }

    public void changeBankPercent(double newPercent) {
        percent = newPercent;
    }

    public void changeMaximumAvailableAmount(double newAmount) {
        maximumAvailableAmount = newAmount;
    }

    public IAccount getAccountById(UUID id) throws BanksException {
        for (IAccount account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        throw new BanksException("There is no account with that number.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getInitialPercent() {
        return initialPercent;
    }

    public void setInitialPercent(double initialPercent) {
        this.initialPercent = initialPercent;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getMaximumAvailableAmount() {
        return maximumAvailableAmount;
    }

    public void setMaximumAvailableAmount(double maximumAvailableAmount) {
        this.maximumAvailableAmount = maximumAvailableAmount;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public List<IAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<IAccount> accounts) {
        this.accounts = accounts;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
