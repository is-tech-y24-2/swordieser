package ru.itmo.banks.Transactions;

import ru.itmo.banks.Accounts.BaseAccount;
import ru.itmo.banks.Exceptions.AlreadyCanceledTransactionException;
import ru.itmo.banks.Exceptions.InvalidTransactionAmountException;
import ru.itmo.banks.Exceptions.WithdrawException;

public class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(BaseAccount sender, double amount, int id)
            throws InvalidTransactionAmountException, WithdrawException {
        if (amount <= 0) {
            throw new InvalidTransactionAmountException();
        }

        if (sender.getMaxWithdraw() != 0 && amount > sender.getMaxWithdraw()) {
            throw new WithdrawException(
                    String.format("Your account is doubtful so you can't withdraw more than %f",
                            sender.getMaxWithdraw()));
        }

        if (sender.getBalance() + sender.getCreditLimit() < amount) {
            throw new WithdrawException(
                    String.format("You haven't enough money: your balance is %f and your credit limit is %f",
                            sender.getBalance(), sender.getCreditLimit()));
        }


        this.sender = sender;
        this.amount = amount;
        this.id = id;
        this.recipient = null;
        sender.Withdraw(amount);
    }

    @Override
    public void Cancel() throws AlreadyCanceledTransactionException {
        if (isCanceled) {
            throw new AlreadyCanceledTransactionException();
        }

        this.sender.Replenishment(this.amount);
        isCanceled = true;
    }
}
