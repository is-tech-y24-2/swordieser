package ru.itmo.banks.Messages;

public class PercentBankMessage implements BankMessage {
    @Override
    public String Message(double amount) {
        return String.format("Percent fas changed to %f", amount);
    }
}
