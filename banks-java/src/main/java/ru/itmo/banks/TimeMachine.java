package ru.itmo.banks;

import ru.itmo.banks.banks.CentralBank;

import java.time.LocalDate;

public class TimeMachine {
    public static void TimeRewind(CentralBank centralBank, LocalDate dateTime) {
        centralBank.NotifyBanks(dateTime);
    }
}
