package ru.itmo.banks.message;

public class CreditLimitBankMessage implements BankMessage {
    @Override
    public String message(double amount) {
        return String.format("Credit limit has changed to %f", amount);
    }
}
