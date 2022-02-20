package ru.itmo.banks;

import ru.itmo.banks.Banks.CentralBank;

import java.time.LocalDate;

public class TimeMachine {
    public static void TimeRewind(CentralBank centralBank, LocalDate dateTime) {
        centralBank.NotifyBanks(dateTime);
    }
}
