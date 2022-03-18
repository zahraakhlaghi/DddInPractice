package com.example.dddinpractice.Atms;

import com.example.dddinpractice.Common.BaseAggregationRoot;
import com.example.dddinpractice.SharedKernel.Money;

import javax.persistence.Entity;

@Entity
public class Atm extends BaseAggregationRoot {

    private static final Double CommissionRate = 0.01;

    private Money moneyInside = Money.None;
    private Double moneyCharged = 0.0;

    public String CanTakeMoney(Double amount) {

        if (amount <= 0)
            return "Invalid amount";
        if (moneyInside.Amount() < amount)
            return "Not enough money";

        if (!moneyInside.CanAllocate(amount))
            return "Not enough change";
        return null;

    }

    public void TakeMoney(Double amount) {

        if (CanTakeMoney(amount) != null)
            throw new IllegalArgumentException();
        Money output = moneyInside.Allocate(amount);
        moneyInside = Money.Subtract(moneyInside, output);

        Double amountWithCommission = CalculateAmountWithCommission(amount);
        moneyCharged += amountWithCommission;

    }

    public Double CalculateAmountWithCommission(Double amount) {

        Double commission = amount * CommissionRate;
        Double leastThanCent = commission % 0.01;

        if (leastThanCent > 0)
            commission = commission - leastThanCent + 0.01;
        return amount + commission;
    }

    public void LoadMoney(Money money) {
        moneyInside = Money.Sum(moneyInside, money);
    }

    public Money getMoneyInside() {
        return moneyInside;
    }

    public Double getMoneyCharged() {
        return moneyCharged;
    }

    public Atm() {
    }
}
