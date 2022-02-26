package ru.itmo.banks.bank;

import java.util.Map;

public class BankConditions {
    private final Map<Double, Double> percentBorders;
    public double percent;
    public double commission;
    public double creditLimit;
    public double maxTransfer;
    public double maxWithdraw;

    public BankConditions(
            double percent,
            double commission,
            double creditLimit,
            double maxTransfer,
            double maxWithdraw,
            Map<Double, Double> percentsBorders) {
        this.percent = percent;
        this.commission = commission;
        this.creditLimit = creditLimit;
        this.maxTransfer = maxTransfer;
        this.maxWithdraw = maxWithdraw;
        this.percentBorders = percentsBorders;
    }

    public double chooseDepositPercent(double balance) {
        Map.Entry<Double, Double> entry = null;
        for (Map.Entry<Double, Double> ent : percentBorders.entrySet()) {
            entry = ent;
            if (entry.getValue() > balance) {
                return entry.getKey();
            }
        }
        return entry.getKey();
    }
}
