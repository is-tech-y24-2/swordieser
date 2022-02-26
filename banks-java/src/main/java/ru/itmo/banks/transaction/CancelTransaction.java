package ru.itmo.banks.transaction;

public class CancelTransaction extends Transaction {
    public CancelTransaction(Transaction transaction) {
        transaction.cancel();
    }

    @Override
    public void cancel() {
    }
}
