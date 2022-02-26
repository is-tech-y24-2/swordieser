package ru.itmo.banks.transactions;

import ru.itmo.banks.accounts.BaseAccount;
import ru.itmo.banks.exceptions.AlreadyCanceledTransactionException;
import ru.itmo.banks.exceptions.InvalidTransactionAmountException;
import ru.itmo.banks.exceptions.ReplenishmentException;

public class TransferTransaction extends Transaction {
    public TransferTransaction(BaseAccount sender, BaseAccount recipient, double amount, int id) throws InvalidTransactionAmountException, ReplenishmentException {
        this.recipient = recipient;
        this.sender = sender;
        this.amount = amount;
        this.id = id;
        if (amount <= 0) {
            throw new InvalidTransactionAmountException();
        }

        if (sender.getMaxWithdraw() != 0 && amount > sender.getMaxWithdraw()) {
            throw new ReplenishmentException(
                    String.format("Your account is doubtful so you can't transfer more than %f", sender.getMaxTransfer()));
        }

        if (sender.getBalance() + sender.getCreditLimit() < amount) {
            throw new ReplenishmentException(
                    String.format("You haven't enough money: your balance is %f and your credit limit is %f",
                            sender.getBalance(), sender.getCreditLimit()));
        }

        sender.Withdraw(amount);
        recipient.Replenishment(amount);
    }

    @Override
    public void Cancel() throws AlreadyCanceledTransactionException {
        if (isCanceled)
        {
            throw new AlreadyCanceledTransactionException();
        }

        this.sender.Replenishment(this.amount);
        this.recipient.Withdraw(this.amount);
        this.isCanceled = true;
    }
}
