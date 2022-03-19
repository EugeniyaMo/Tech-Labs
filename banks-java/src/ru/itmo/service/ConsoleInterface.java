package ru.itmo.service;

import ru.itmo.account.IAccount;
import ru.itmo.bank.Bank;
import ru.itmo.bank.ICentralBank;
import ru.itmo.bank.Transaction;
import ru.itmo.bank.TypeAccount;
import ru.itmo.client.Client;
import ru.itmo.tools.BanksException;

import java.util.*;

public class ConsoleInterface implements IConsoleInterface {
    private ICentralBank centralBank;
    private Bank bank;
    private HashMap<String, String> dataBasePasswords = new HashMap<>();
    private HashMap<String, Client> dataBaseClients = new HashMap<>();

    public ConsoleInterface(ICentralBank centralBank, Bank bank) {
        this.centralBank = centralBank;
        this.bank = bank;
    }

    public Client greeting() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("Are you already registered in the system? Y/N");
        String answer = scanner.nextLine();
        if (answer.equals("Y")) {
            System.out.println("Enter your username:");
            String username = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            if (dataBasePasswords.get(username) == password) {
                System.out.println("OK");
            }
            else {
                System.out.println("Invalid password");
            }
            return dataBaseClients.get(username);
        } else {
            return registration();
        }
    }

    public Client registration() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Okey, then you can register right now.");
        System.out.println("Enter your new username:");
        String username = scanner.nextLine();
        System.out.println("Enter your new password:");
        String password = scanner.nextLine();
        dataBasePasswords.put(username, password);
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your surname:");
        String surname = scanner.nextLine();
        System.out.println("Do you want to enter an address? Y/N");
        String answer = scanner.nextLine();
        String address = (answer.equals("Y")) ? scanner.nextLine() : null;
        System.out.println("Do you want to enter an passport? Y/N");
        answer = scanner.nextLine();
        String passport = (answer.equals("Y")) ? scanner.nextLine() : null;
        var newClient = new Client(name, surname, address, passport);
        centralBank.addNewClientToBank(newClient, bank);
        return newClient;
    }

    public void createNewAccount(Client client) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What kind of account do you want to create?");
        System.out.println("1 - Debit\n2 - Deposit\n3 - Credit");
        System.out.println("Write name of account:");
        String nameAccount = scanner.nextLine().toLowerCase(Locale.ROOT);
        TypeAccount typeAccount = TypeAccount.getTypeByName(nameAccount);
        System.out.println("Write the amount of money you want to put into the account:");
        double money = scanner.nextDouble();
        centralBank.createNewAccount(client, bank, typeAccount, money);
    }

    public void workingWithAccount(Client client) throws BanksException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you already have an active account? Y/N");
        String answer = scanner.next();
        while (!answer.equals("Y")) {
            createNewAccount(client);
            System.out.println("Do you already have an active account? Y/N");
            answer = scanner.next();
        }

        ArrayList<IAccount> accounts = new ArrayList<IAccount>();
        for (IAccount account : bank.getAccounts()) {
            if (account.getClient() == client) {
                System.out.println(account.getId());
                accounts.add(account);
            }
        }

        System.out.println("Choose necessary account and enter number.");
        int numAccount = scanner.nextInt() - 1;
        UUID accountNumber = accounts.get(numAccount).getId();
        int numOperation = 0;
        while (numOperation != 5) {
            System.out.println("What kind of operation do you want to do?");
            System.out.println("1 - Withdraw money\n2 - Append money\n3 - Transfer money\n4 - Show the balance");
            System.out.println("5 - Cansel transaction\n6 - Change account");
            System.out.println("Write number:");
            numOperation = scanner.nextInt();
            double money;
            switch (numOperation) {
                case 1 -> {
                    System.out.println("Enter amount of money:");
                    money = scanner.nextDouble();
                    accounts.get(numAccount).withdrawMoney(money);
                }
                case 2 -> {
                    System.out.println("Enter amount of money:");
                    money = scanner.nextDouble();
                    accounts.get(numAccount).appendMoney(money);
                }
                case 3 -> {
                    System.out.println("Enter amount of money:");
                    money = scanner.nextDouble();
                    for (IAccount account : bank.getAccounts()) {
                        System.out.println(account.getId());
                    }
                    System.out.println("Choose necessary account and enter number.");
                    int num = scanner.nextInt();
                    accounts.get(numAccount).transferMoney(accounts.get(num).getId(), money);
                }
                case 4 -> {
                    System.out.println(accounts.get(numAccount).getSize());
                }
                case 5 -> {
                    ArrayList<Transaction> transactions = new ArrayList<Transaction>();
                    for (Transaction transaction : bank.getTransactions()) {
                        if (transaction.getIdAccount() == accounts.get(numAccount).getId()) {
                            System.out.println(transaction.getId());
                            transactions.add(transaction);
                        }
                    }
                    System.out.println("Choose necessary transaction and enter number.");
                    int numTransaction = scanner.nextInt() - 1;
                    Transaction currentTransaction = transactions.get(numTransaction);
                    if (currentTransaction.getIdRecipient() == null) {
                        bank.getAccountById(currentTransaction.getIdAccount()).
                                appendMoney(currentTransaction.getAmount());
                        bank.getTransactions().remove(bank.getTransactions().indexOf(currentTransaction));
                    } else {
                        bank.getAccountById(currentTransaction.getIdAccount()).
                                appendMoney(currentTransaction.getAmount());
                        bank.getAccountById(currentTransaction.getIdRecipient()).
                                withdrawMoney(currentTransaction.getAmount());
                        bank.getTransactions().remove(bank.getTransactions().indexOf(currentTransaction));
                    }
                }
            }
        }
    }
}
