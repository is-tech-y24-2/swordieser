package ru.itmo.banks.message;

public class TransferLimitBankMessage implements BankMessage {
    @Override
    public String message(double amount) {
        return String.format("Transfer limit has changed to %f", amount);
    }
}
