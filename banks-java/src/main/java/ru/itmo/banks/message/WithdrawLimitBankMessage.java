package ru.itmo.banks.message;

public class WithdrawLimitBankMessage implements BankMessage {
    @Override
    public String message(double amount) {
        return String.format("Withdraw limit has changed to %f", amount);
    }
}
