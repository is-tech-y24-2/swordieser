package ru.itmo.banks.bank;

import ru.itmo.banks.account.BaseAccount;
import ru.itmo.banks.account.CreditAccount;
import ru.itmo.banks.account.DebitAccount;
import ru.itmo.banks.account.DepositAccount;
import ru.itmo.banks.client.Person;
import ru.itmo.banks.exception.AlreadyRegisteredObserverException;
import ru.itmo.banks.exception.NotEndedDepositAccountException;
import ru.itmo.banks.exception.NotRegisteredObserverException;
import ru.itmo.banks.message.*;
import ru.itmo.banks.transaction.*;

import java.time.LocalDate;
import java.util.*;

public class Bank {
    private static int banksId = 1;
    private static int accountsCounter = 1;
    private final List<BaseAccount> observers;
    private final List<BaseAccount> accounts;
    private final BankConditions conditions;
    private final int id;
    private final String name;

    public Bank(
            String name,
            double percent,
            double commission,
            double creditLimit,
            double maxWithdraw,
            double maxTransfer,
            Map<Double, Double> percentsBorders) {
        this.id = banksId++;
        this.name = name;
        this.conditions = new BankConditions(
                percent,
                commission,
                creditLimit,
                maxTransfer,
                maxWithdraw,
                percentsBorders);
        this.accounts = new ArrayList<BaseAccount>();
        this.observers = new ArrayList<BaseAccount>();
    }


    public void registerObserver(BaseAccount observer) {
        if (observers.contains(observer)) {
            throw new AlreadyRegisteredObserverException();
        }

        this.observers.add(observer);
    }

    public void removeObserver(BaseAccount observer) {
        if (!observers.contains(observer)) {
            throw new NotRegisteredObserverException();
        }

        this.observers.remove(observer);
    }

    public void sendNotify(List<BaseAccount> observers, double amount, BankMessage message) {
        for (BaseAccount observer : observers) {
            observer.update(message.message(amount));
        }
    }

    public BaseAccount createDebitAccount(Person person, double startBalance) {
        var account = new DebitAccount(
                accountsCounter++,
                conditions,
                startBalance);

        this.accounts.add(account);
        person.addNewAccount(account);
        person.checkDoubtfulness();

        return account;
    }

    public BaseAccount createDepositAccount(Person person, double startBalance, LocalDate end) {
        var account = new DepositAccount(
                accountsCounter++,
                conditions,
                startBalance,
                end);

        this.accounts.add(account);
        person.addNewAccount(account);
        person.checkDoubtfulness();

        return account;
    }

    public BaseAccount createCreditAccount(Person person, double startBalance) {
        var account = new CreditAccount(
                accountsCounter++,
                conditions,
                startBalance);

        this.accounts.add(account);
        person.addNewAccount(account);
        person.checkDoubtfulness();

        return account;
    }

    public void setMaxTransfer(double amount) {
        var tempObservers = new ArrayList<BaseAccount>();
        for (BaseAccount account : this.accounts) {
            if (account.getMaxTransfer() != 0) {
                tempObservers.add(account);
            }
        }
        this.conditions.maxTransfer = amount;
        sendNotify(tempObservers, amount, new TransferLimitBankMessage());
    }

    public void setMaxWithdraw(double amount) {
        var tempObservers = new ArrayList<BaseAccount>();
        for (BaseAccount account : this.accounts) {
            if (account.getMaxWithdraw() != 0) {
                tempObservers.add(account);
            }
        }
        this.conditions.maxWithdraw = amount;
        sendNotify(tempObservers, amount, new WithdrawLimitBankMessage());
    }

    public void setCreditLimit(double amount) {
        var tempObservers = new ArrayList<BaseAccount>();
        for (BaseAccount account : this.accounts) {
            if (account.getCreditLimit() != 0) {
                tempObservers.add(account);
            }
        }
        sendNotify(tempObservers, amount, new CreditLimitBankMessage());
    }

    public void setPercent(double amount) {
        var tempObservers = new ArrayList<BaseAccount>();
        for (BaseAccount account : this.accounts) {
            if (account.getPercent() != 0) {
                tempObservers.add(account);
            }
        }
        sendNotify(tempObservers, amount, new PercentBankMessage());
    }

    public void replenishment(BaseAccount account, double amount) {
        var trans = new ReplenishmentTransaction(account, amount, account.getTransactionId());
        account.addTransaction(trans);
    }

    public void withdraw(BaseAccount account, double amount) {
        if (account.getAccountPeriod() != LocalDate.MIN && account.getAccountPeriod().isBefore(LocalDate.now())) {
            throw new NotEndedDepositAccountException();
        }

        var trans = new WithdrawTransaction(account, amount, account.getTransactionId());
        account.addTransaction(trans);
    }

    public void transfer(BaseAccount sender, BaseAccount recipient, double amount) {
        if (sender.getAccountPeriod() != LocalDate.MIN && sender.getAccountPeriod().isBefore(LocalDate.now())) {
            throw new NotEndedDepositAccountException();
        }

        var trans = new TransferTransaction(sender, recipient, amount, sender.getTransactionId());
        sender.addTransaction(trans);
        recipient.addTransaction(trans);
    }

    public void cancellation(BaseAccount account, Transaction transaction) {
        var trans = new CancelTransaction(transaction);
        account.addTransaction(trans);
    }

    public void updateBalance(LocalDate dateTime) {
        var updatingAccounts = new ArrayList<BaseAccount>();
        for (BaseAccount account : this.accounts) {
            if (account.getAccountPeriod() != java.time.LocalDate.MIN) {
                updatingAccounts.add(account);
            }
        }
        updatingAccounts.forEach(account -> account.balanceUpdate(dateTime));
    }

    public Collection<BaseAccount> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public BankConditions getConditions() {
        return conditions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
