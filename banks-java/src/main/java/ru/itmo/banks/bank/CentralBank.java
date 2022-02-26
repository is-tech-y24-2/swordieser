package ru.itmo.banks.bank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CentralBank {
    private static CentralBank instance;
    private List<Bank> banks;
    private String name;

    private CentralBank(String name) {
        this.name = name;
        this.banks = new ArrayList<Bank>();
    }

    public static CentralBank getInstance() {
        if (instance == null) {
            return new CentralBank("central bank");
        }
        return instance;
    }

    public Bank createBank(
            String name,
            double percent,
            double commission,
            double creditLimit,
            double maxWithdraw,
            double maxTransfer,
            Map<Double, Double> percentsBorders) {
        var bank = new Bank(name, percent, commission, creditLimit, maxWithdraw, maxTransfer, percentsBorders);
        banks.add(bank);
        return bank;
    }

    public List<Bank> getBanks() {
        return Collections.unmodifiableList(banks);
    }

    public void notifyBanks(LocalDate dateTime) {
        for (Bank bank : banks) {
            bank.updateBalance(dateTime);
        }
    }
}
