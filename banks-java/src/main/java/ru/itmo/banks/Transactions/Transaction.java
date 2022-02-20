package ru.itmo.banks.Transactions;

import ru.itmo.banks.Accounts.BaseAccount;
import ru.itmo.banks.Exceptions.AlreadyCanceledTransactionException;

public abstract class Transaction {
    protected BaseAccount sender;
    protected BaseAccount recipient;
    protected double amount;
    protected int id;
    protected boolean isCanceled;

    public abstract void Cancel() throws AlreadyCanceledTransactionException;

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
