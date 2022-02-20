package ru.itmo.banks.Messages;

public class CreditLimitBankMessage implements BankMessage {
    @Override
    public String Message(double amount) {
        return String.format("Credit limit has changed to %f", amount);
    }
}