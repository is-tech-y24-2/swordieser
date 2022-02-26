package ru.itmo.banks;

import ru.itmo.banks.bank.CentralBank;

import java.time.LocalDate;

public class TimeMachine {
    public static void timeRewind(CentralBank centralBank, LocalDate dateTime) {
        centralBank.notifyBanks(dateTime);
    }
}
