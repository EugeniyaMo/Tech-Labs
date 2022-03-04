package ru.itmo.bank;

import ru.itmo.account.CreditAccount;
import ru.itmo.account.DebitAccount;
import ru.itmo.account.DepositAccount;
import ru.itmo.account.IAccount;
import ru.itmo.client.Client;

import java.util.ArrayList;
import java.util.UUID;

public class CentralBank implements ICentralBank {
    private ArrayList<Bank> banks = new ArrayList<Bank>();
    private ArrayList<Client> clients = new ArrayList<Client>();
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public Bank registerNewBank(Bank bank) {
        banks.add(bank);
        return bank;
    }

    public Client addNewClientToBank(Client client, Bank bank) {
        clients.add(client);
        bank.getClients().add(client);
        return client;
    }

    public IAccount createNewAccount(Client client, Bank bank, int numberAccount, double size) {
        IAccount newAccount = null;
        switch (numberAccount) {
            // Debit Account
            case 1:
                newAccount = new DebitAccount(bank, client, size);
                bank.getAccounts().add(newAccount);
                break;

            // Deposit Account
            case 2:
                newAccount = new DepositAccount(bank, client, size);
                bank.getAccounts().add(newAccount);
                break;

            // Credit Account
            case 3:
                newAccount = new CreditAccount(bank, client, size);
                bank.getAccounts().add(newAccount);
                break;
        }
        return newAccount;
    }

    public void notifyUpdateAccountSize(Bank bank) {
        for (IAccount account : bank.getAccounts()) {
            account.updateAccountSize(true);
        }
    }

    public void canselTransaction(Bank bank, UUID transactionID) {
        int index = -1;
        for (Transaction transaction : transactions) {
            if (transaction.getId() == transactionID)
                index = transactions.indexOf(transaction);
        }
        transactions.remove(index);
    }
}
