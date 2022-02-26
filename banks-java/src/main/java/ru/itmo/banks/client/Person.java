package ru.itmo.banks.client;

import ru.itmo.banks.account.BaseAccount;

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
        checkDoubtfulness();
    }


    public void addNewAccount(BaseAccount account) {
        accounts.add(account);
    }

    public List<BaseAccount> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void setAddress(String address) {
        this.address = address;
        checkDoubtfulness();
    }

    public void setPassport(long passport) {
        this.passport = passport;
        checkDoubtfulness();
    }

    public void checkDoubtfulness() {
        if (!this.address.isEmpty() && this.passport != 0) {
            this.doubtful = false;
            for (BaseAccount account : accounts) {
                account.setMaxWithdraw(0);
                account.setMaxTransfer(0);
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
