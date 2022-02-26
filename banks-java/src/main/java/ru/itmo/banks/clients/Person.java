package ru.itmo.banks.clients;

import ru.itmo.banks.accounts.BaseAccount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {
    private List<BaseAccount> accounts;
    private String name;
    private String surname;
    private String address;
    private long passport;
    private boolean doubtful;

    public Person(String name, String surname, String address, long passport) {
        accounts = new ArrayList<BaseAccount>();
        doubtful = true;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passport = passport;
        CheckDoubtfulness();
    }


    public void AddNewAccount(BaseAccount account) {
        accounts.add(account);
    }

    public List<BaseAccount> GetAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void SetAddress(String address) {
        this.address = address;
        CheckDoubtfulness();
    }

    public void SetPassport(long passport) {
        this.passport = passport;
        CheckDoubtfulness();
    }

    public void CheckDoubtfulness() {
        if (!this.address.isEmpty() && this.passport != 0) {
            this.doubtful = false;
            for (BaseAccount account : accounts) {
                account.SetMaxWithdraw(0);
                account.SetMaxTransfer(0);
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
