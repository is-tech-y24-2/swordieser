package ru.itmo.banks.transaction;

import ru.itmo.banks.exception.AlreadyCanceledTransactionException;

public class CancelTransaction extends Transaction {
    public CancelTransaction(Transaction transaction) throws AlreadyCanceledTransactionException {
        transaction.Cancel();
    }

    @Override
    public void Cancel() {
    }
}
