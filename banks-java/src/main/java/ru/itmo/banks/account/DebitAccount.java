package ru.itmo.banks.account;

import ru.itmo.banks.bank.BankConditions;
import ru.itmo.banks.transaction.Transaction;

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
