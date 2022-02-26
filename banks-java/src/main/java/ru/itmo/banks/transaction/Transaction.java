package ru.itmo.banks.transaction;

import ru.itmo.banks.account.BaseAccount;
import ru.itmo.banks.exception.AlreadyCanceledTransactionException;

public abstract class Transaction {
    protected BaseAccount sender;
    protected BaseAccount recipient;
    protected double amount;
    protected int id;
    protected boolean isCanceled;

    public abstract void Cancel();

    public BaseAccount getSender() {
        return sender;
    }

    public BaseAccount getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public boolean isCanceled() {
        return isCanceled;
    }
}
