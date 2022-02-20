package ru.itmo.banks.Accounts;

import ru.itmo.banks.Banks.BankConditions;
import ru.itmo.banks.Transactions.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreditAccount extends BaseAccount {
    public CreditAccount(
            long id,
            BankConditions conditions,
            double startBalance) {
        this.creditLimit = conditions.creditLimit;
        this.commission = conditions.commission;
        this.id = id;
        this.maxTransfer = conditions.maxTransfer;
        this.maxWithdraw = conditions.maxWithdraw;
        this.balance = startBalance;
        this.messagesList = new ArrayList<String>();
        this.transactionsHistory = new ArrayList<Transaction>();
        this.accountPeriod = LocalDate.MIN;
    }

    @Override
    public void BalanceUpdate(LocalDate dateTime) {
        LocalDate date = LocalDate.now();
        double daysUntilEnd = dateTime.until(date).getDays();

        if (this.commission != 0 && this.balance < 0) {
            for (int i = 0; i < daysUntilEnd; i++) {
                this.monthlyCommission += this.commission;
                date = date.plusDays(1);

                if (date.getDayOfMonth() == 1) {
                    Withdraw(this.monthlyCommission);
                    this.monthlyCommission = 0;
                }
            }
        }
    }
}
