package ru.itmo.banks.Accounts;

import ru.itmo.banks.Banks.BankConditions;
import ru.itmo.banks.Transactions.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DepositAccount extends BaseAccount {
    public DepositAccount(
            long id,
            BankConditions conditions,
            double startBalance,
            LocalDate endOfAccount) {
        this.id = id;
        this.percent = conditions.ChooseDepositPercent(startBalance);
        this.maxTransfer = conditions.maxTransfer;
        this.maxWithdraw = conditions.maxWithdraw;
        this.balance = startBalance;
        this.transactionsHistory = new ArrayList<Transaction>();
        this.messagesList = new ArrayList<String>();
        this.commission = 0;
        this.creditLimit = 0;
        this.accountPeriod = endOfAccount;
    }
}
