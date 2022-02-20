package ru.itmo.banks.Accounts;

import ru.itmo.banks.Banks.BankConditions;
import ru.itmo.banks.Transactions.Transaction;

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
