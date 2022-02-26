package ru.itmo.banks.accounts;

import ru.itmo.banks.banks.BankConditions;
import ru.itmo.banks.transactions.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;

public class DebitAccount extends BaseAccount{
    public DebitAccount(
            long id,
            BankConditions conditions,
            double startBalance){
        this.id = id;
        this.percent = conditions.percent;
        this.maxTransfer = conditions.maxTransfer;
        this.maxWithdraw = conditions.maxWithdraw;
        this.balance = startBalance;
        this.messagesList = new ArrayList<String>();
        this.transactionsHistory = new ArrayList<Transaction>();
        this.accountPeriod = LocalDate.MIN;
    }

}
