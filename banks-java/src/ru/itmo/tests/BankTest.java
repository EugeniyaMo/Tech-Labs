package ru.itmo.tests;

import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.itmo.account.IAccount;
import ru.itmo.bank.Bank;
import ru.itmo.bank.CentralBank;
import ru.itmo.bank.ICentralBank;
import ru.itmo.bank.TypeAccount;
import ru.itmo.client.Client;
import ru.itmo.tools.BanksException;

import static java.lang.Math.abs;

public class BankTest {
    private ICentralBank centralBank;
    double epsilon = 0.00000001;

    @BeforeTest
    public void setUp() {
        centralBank = new CentralBank();
    }

    @Test
    public void createNewBank() {
        Bank newBank = new Bank("Sberbank", 13.1, 3, 15000,
                5000, 100000);
        centralBank.registerNewBank(newBank);
        Assert.assertNotEquals(centralBank.getBanks().indexOf(newBank), -1);
    }

    @Test
    public void createNewClient_addClientToBank() {
        Bank bank = new Bank("Sberbank", 13.1, 3, 15000,
                5000, 100000);
        centralBank.registerNewBank(bank);
        Client newClient = new Client("Sergey", "Potapov");
        centralBank.addNewClientToBank(newClient, bank);
        Assert.assertNotEquals(bank.getClients().indexOf(newClient), -1);
    }

    @Test
    public void createNewDebitAccount_doTransactions() throws BanksException {
        Bank bank = new Bank("Sberbank", 13.1, 3, 15000,
                5000, 100000);
        centralBank.registerNewBank(bank);
        Client newClient = new Client("Sergey", "Potapov");
        centralBank.addNewClientToBank(newClient, bank);
        IAccount newAccount = centralBank.createNewAccount(newClient, bank, TypeAccount.DEBIT, 40000);
        newAccount.appendMoney(1000);
        newAccount.withdrawMoney(200);
        Assert.assertTrue(abs(newAccount.getSize() - (40000 + 1000 - 200)) < epsilon);
        //Assert.assertEquals(newAccount.getSize(), 40000 + 1000 - 200);
    }

    @Test(expectedExceptions = BanksException.class)
    public void createNewAccount_notEnoughMoney_throwException() throws BanksException {
        Bank bank = new Bank("Sberbank", 13.1, 3, 15000,
                5000, 100000);
        centralBank.registerNewBank(bank);
        Client newClient = new Client("Sergey", "Potapov");
        centralBank.addNewClientToBank(newClient, bank);
        IAccount newAccount = centralBank.createNewAccount(newClient, bank, TypeAccount.DEBIT, 1000);
        newAccount.withdrawMoney(2000);
    }

    @Test(expectedExceptions = BanksException.class)
    public void createNewDoubtfulAccount_throwException() throws BanksException {
        Bank bank = new Bank("Sberbank", 13.1, 3, 15000,
                5000, 100000);
        centralBank.registerNewBank(bank);
        Client newClient = new Client("Sergey", "Potapov");
        centralBank.addNewClientToBank(newClient, bank);
        IAccount newAccount = centralBank.createNewAccount(newClient, bank, TypeAccount.DEBIT, 40000);
        newAccount.withdrawMoney(6000);
    }
}
