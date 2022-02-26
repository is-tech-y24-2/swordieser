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
        recipient.replenishment(amount);
    }

    @Override
    public void cancel() {
        if (isCanceled) {
            throw new AlreadyCanceledTransactionException();
        }
        this.recipient.withdraw(amount);
        isCanceled = true;
    }
}
