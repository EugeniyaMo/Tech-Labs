package ru.itmo.bank;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private UUID idAccount;
    private UUID idRecipient;
    private double amount;

    public Transaction(UUID idAccount, double amount) {
        id = UUID.randomUUID();
        this.idAccount = idAccount;
        this.amount = amount;
        this.idRecipient = null;
    }

    public Transaction(UUID idAccount, UUID idRecipient, double amount) {
        id = UUID.randomUUID();
        this.idAccount = idAccount;
        this.idRecipient = idRecipient;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public UUID getIdAccount() {
        return idAccount;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setIdAccount(UUID idAccount) {
        this.idAccount = idAccount;
    }

    public UUID getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(UUID idRecipient) {
        this.idRecipient = idRecipient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
