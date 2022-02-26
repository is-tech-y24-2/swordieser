package ru.itmo.banks.message;

public class PercentBankMessage implements BankMessage {
    @Override
    public String message(double amount) {
        return String.format("Percent fas changed to %f", amount);
    }
}
