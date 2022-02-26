package ru.itmo.banks.account;

import ru.itmo.banks.transaction.Transaction;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public abstract class BaseAccount {

    protected long id;
    protected double balance;
    protected double percent;
    protected double commission;
    protected double creditLimit;
    protected double monthlyPercentage;
    protected double monthlyCommission;
    protected double maxWithdraw;
    protected double maxTransfer;
    protected List<String> messagesList;
    protected int transactionId;
    protected List<Transaction> transactionsHistory;
    protected LocalDate accountPeriod;

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public double getPercent() {
        return percent;
    }

    public double getCommission() {
        return commission;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getMonthlyPercentage() {
        return monthlyPercentage;
    }

    public double getMonthlyCommission() {
        return monthlyCommission;
    }

    public double getMaxWithdraw() {
        return maxWithdraw;
    }

    public double getMaxTransfer() {
        return maxTransfer;
    }

    public List<String> getMessagesList() {
        return messagesList;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public LocalDate getAccountPeriod() {
        return accountPeriod;
    }

    public void Replenishment(double amount) {
        balance += amount;
    }

    public void Withdraw(double amount) {
        balance -= amount;
    }

    public void Update(String message) {
        messagesList.add(message);
    }

    public void AddTransaction(Transaction t) {
        transactionsHistory.add(t);
        this.transactionId++;
    }

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
        } else {
            for (int i = 0; i < daysUntilEnd; i++) {
                int daysInYear = date.isLeapYear() ? 366 : 365;
                this.monthlyPercentage += this.balance
                        * Double.parseDouble(String.format("%.2f", this.percent / daysInYear).replace(",", "."));
                date = date.plusDays(1);
                if (date.getDayOfMonth() == 1) {
                    Replenishment(this.monthlyPercentage);
                    this.monthlyPercentage = 0;
                }
            }
        }
    }

    public List<Transaction> GetTransactionsHistory(){
        return Collections.unmodifiableList(transactionsHistory);
    }

    public void SetMaxWithdraw(double amount) {
        maxWithdraw = amount;
    }

    public void SetMaxTransfer(double amount) {
        maxTransfer = amount;
    }


}
