package ru.itmo.banks.messages;

public class TransferLimitBankMessage implements BankMessage {
    @Override
    public String Message(double amount) {
        return String.format("Transfer limit has changed to %f", amount);
    }
}
