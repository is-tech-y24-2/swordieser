package ru.itmo.banks.Transactions;

import ru.itmo.banks.Exceptions.AlreadyCanceledTransactionException;

public class CancelTransaction extends Transaction {
    public CancelTransaction(Transaction transaction) throws AlreadyCanceledTransactionException {
        transaction.Cancel();
    }

    @Override
    public void Cancel() {
    }
}
