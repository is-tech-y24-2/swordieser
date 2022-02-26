package ru.itmo.banks.transaction;

import ru.itmo.banks.account.BaseAccount;
import ru.itmo.banks.exception.AlreadyCanceledTransactionException;
import ru.itmo.banks.exception.InvalidTransactionAmountException;

public class ReplenishmentTransaction extends Transaction {
    public ReplenishmentTransaction(BaseAccount recipient, double amount, int id) {
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
