package ru.itmo.banks.exception;

public class WithdrawException extends RuntimeException {
    public WithdrawException(String string) {
        super(string);
    }
}
