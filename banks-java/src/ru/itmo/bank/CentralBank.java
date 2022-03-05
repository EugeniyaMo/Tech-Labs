package ru.itmo.bank;

import ru.itmo.account.CreditAccount;
import ru.itmo.account.DebitAccount;
import ru.itmo.account.DepositAccount;
import ru.itmo.account.IAccount;
import ru.itmo.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CentralBank implements ICentralBank {
    private List<Bank> banks = new ArrayList<Bank>();
    private List<Client> clients = new ArrayList<Client>();
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public List<Bank> getBanks() {
        return banks;
    }

    public List<Client> getClients() {
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

    public IAccount createNewAccount(Client client, Bank bank, TypeAccount typeAccount, double size) {
        IAccount newAccount = null;
        switch (typeAccount) {
            case DEBIT:
                newAccount = new DebitAccount(bank, client, size);
                bank.getAccounts().add(newAccount);
                break;

            // Deposit Account
            case DEPOSIT:
                newAccount = new DepositAccount(bank, client, size);
                bank.getAccounts().add(newAccount);
                break;

            // Credit Account
            case CREDIT:
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
            if (transaction.getId() == transactionID) {
                index = transactions.indexOf(transaction);
            }
        }
        transactions.remove(index);
    }
}
