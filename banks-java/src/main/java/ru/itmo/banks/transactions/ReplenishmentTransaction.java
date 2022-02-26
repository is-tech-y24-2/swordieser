package ru.itmo.banks.transactions;

import ru.itmo.banks.accounts.BaseAccount;
import ru.itmo.banks.exceptions.AlreadyCanceledTransactionException;
import ru.itmo.banks.exceptions.InvalidTransactionAmountException;

public class ReplenishmentTransaction extends Transaction {
    public ReplenishmentTransaction(BaseAccount recipient, double amount, int id) throws InvalidTransactionAmountException {
        if (amount <= 0) {
            throw new InvalidTransactionAmountException();
        }
        this.recipient = recipient;
        this.amount = amount;
        this.id = id;
        this.sender = null;
        recipient.Replenishment(amount);
    }

    @Override
    public void Cancel() throws AlreadyCanceledTransactionException {
        if (isCanceled) {
            throw new AlreadyCanceledTransactionException();
        }
        this.recipient.Withdraw(amount);
        isCanceled = true;
    }
}
