package ru.itmo.banks.Banks;

import ru.itmo.banks.Accounts.BaseAccount;
import ru.itmo.banks.Accounts.CreditAccount;
import ru.itmo.banks.Accounts.DebitAccount;
import ru.itmo.banks.Accounts.DepositAccount;
import ru.itmo.banks.Clients.Person;
import ru.itmo.banks.Exceptions.*;
import ru.itmo.banks.Messages.*;
import ru.itmo.banks.Transactions.*;

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


    public void RegisterObserver(BaseAccount observer) throws AlreadyRegisteredObserverException {
        if (observers.contains(observer)) {
            throw new AlreadyRegisteredObserverException();
        }

        this.observers.add(observer);
    }

    public void RemoveObserver(BaseAccount observer) throws NotRegisteredObserverException {
        if (!observers.contains(observer)) {
            throw new NotRegisteredObserverException();
        }

        this.observers.remove(observer);
    }

    public void SendNotify(List<BaseAccount> observers, double amount, BankMessage message) {
        for (BaseAccount observer : observers) {
            observer.Update(message.Message(amount));
        }
    }

    public BaseAccount CreateDebitAccount(Person person, double startBalance) {
        var account = new DebitAccount(
                accountsCounter++,
                conditions,
                startBalance);

        this.accounts.add(account);
        person.AddNewAccount(account);
        person.CheckDoubtfulness();

        return account;
    }

    public BaseAccount CreateDepositAccount(Person person, double startBalance, LocalDate end) {
        var account = new DepositAccount(
                accountsCounter++,
                conditions,
                startBalance,
                end);

        this.accounts.add(account);
        person.AddNewAccount(account);
        person.CheckDoubtfulness();

        return account;
    }

    public BaseAccount CreateCreditAccount(Person person, double startBalance) {
        var account = new CreditAccount(
                accountsCounter++,
                conditions,
                startBalance);

        this.accounts.add(account);
        person.AddNewAccount(account);
        person.CheckDoubtfulness();

        return account;
    }

    public void SetMaxTransfer(double amount) {
        var tempObservers = new ArrayList<BaseAccount>();
        for (BaseAccount account : this.accounts) {
            if (account.getMaxTransfer() != 0) {
                tempObservers.add(account);
            }
        }
        this.conditions.maxTransfer = amount;
        SendNotify(tempObservers, amount, new TransferLimitBankMessage());
    }

    public void SetMaxWithdraw(double amount) {
        var tempObservers = new ArrayList<BaseAccount>();
        for (BaseAccount account : this.accounts) {
            if (account.getMaxWithdraw() != 0) {
                tempObservers.add(account);
            }
        }
        this.conditions.maxWithdraw = amount;
        SendNotify(tempObservers, amount, new WithdrawLimitBankMessage());
    }

    public void SetCreditLimit(double amount) {
        var tempObservers = new ArrayList<BaseAccount>();
        for (BaseAccount account : this.accounts) {
            if (account.getCreditLimit() != 0) {
                tempObservers.add(account);
            }
        }
        SendNotify(tempObservers, amount, new CreditLimitBankMessage());
    }

    public void SetPercent(double amount) {
        var tempObservers = new ArrayList<BaseAccount>();
        for (BaseAccount account : this.accounts) {
            if (account.getPercent() != 0) {
                tempObservers.add(account);
            }
        }
        SendNotify(tempObservers, amount, new PercentBankMessage());
    }

    public void Replenishment(BaseAccount account, double amount) throws InvalidTransactionAmountException {
        var trans = new ReplenishmentTransaction(account, amount, account.getTransactionId());
        account.AddTransaction(trans);
    }

    public void Withdraw(BaseAccount account, double amount) throws NotEndedDepositAccountException, WithdrawException, InvalidTransactionAmountException {
        if (account.getAccountPeriod() != LocalDate.MIN && account.getAccountPeriod().isBefore(LocalDate.now())) {
            throw new NotEndedDepositAccountException();
        }

        var trans = new WithdrawTransaction(account, amount, account.getTransactionId());
        account.AddTransaction(trans);
    }

    public void Transfer(BaseAccount sender, BaseAccount recipient, double amount) throws NotEndedDepositAccountException, ReplenishmentException, InvalidTransactionAmountException {
        if (sender.getAccountPeriod() != LocalDate.MIN && sender.getAccountPeriod().isBefore(LocalDate.now())) {
            throw new NotEndedDepositAccountException();
        }

        var trans = new TransferTransaction(sender, recipient, amount, sender.getTransactionId());
        sender.AddTransaction(trans);
        recipient.AddTransaction(trans);
    }

    public void Cancellation(BaseAccount account, Transaction transaction) throws AlreadyCanceledTransactionException {
        var trans = new CancelTransaction(transaction);
        account.AddTransaction(trans);
    }

    public void UpdateBalance(LocalDate dateTime) {
        var updatingAccounts = new ArrayList<BaseAccount>();
        for (BaseAccount account : this.accounts) {
            if (account.getAccountPeriod() != java.time.LocalDate.MIN) {
                updatingAccounts.add(account);
            }
        }
        updatingAccounts.forEach(account -> account.BalanceUpdate(dateTime));
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
