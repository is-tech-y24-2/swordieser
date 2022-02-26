package ru.itmo.banks.messages;

public class WithdrawLimitBankMessage implements BankMessage {
    @Override
    public String Message(double amount) {
        return String.format("Withdraw limit has changed to %f", amount);
    }
}
