package ru.itmo;

import ru.itmo.bank.Bank;
import ru.itmo.bank.CentralBank;
import ru.itmo.bank.ICentralBank;
import ru.itmo.client.Client;
import ru.itmo.service.ConsoleInterface;
import ru.itmo.service.IConsoleInterface;
import ru.itmo.tools.BanksException;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws BanksException {
        ICentralBank centralBank = new CentralBank();

        // Create main bank
        Bank bank = new Bank("Tinkoff", 9.7, 4, 7000, 1500,
                200000);
        centralBank.registerNewBank(bank);
        Date startTime = new Date();
        IConsoleInterface consoleInterface = new ConsoleInterface(centralBank, bank);
        Client client = consoleInterface.greeting();
        while (true)
        {
            consoleInterface.workingWithAccount(client);
        }
    }
}
