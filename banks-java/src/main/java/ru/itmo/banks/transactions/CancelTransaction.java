package ru.itmo.banks.transactions;

import ru.itmo.banks.exceptions.AlreadyCanceledTransactionException;

public class CancelTransaction extends Transaction {
    public CancelTransaction(Transaction transaction) throws AlreadyCanceledTransactionException {
        transaction.Cancel();
    }

    @Override
    public void Cancel() {
    }
}
