package com.example.dddinpractice;

import javax.persistence.*;
import javax.persistence.*;
import java.util.List;

@Entity
public class SnackMachine extends MyEntity {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "OneCentCount", column = @Column(name = "MoneyInside_OneCentCount")),
            @AttributeOverride(name = "TenCentCount", column = @Column(name = "MoneyInside_TenCentCount")),
            @AttributeOverride(name = "QuarterCount", column = @Column(name = "MoneyInside_QuarterCount")),
            @AttributeOverride(name = "OneDollarCount", column = @Column(name = "MoneyInside_OneDollarCount")),
            @AttributeOverride(name = "FiveDollarCount", column = @Column(name = "MoneyInside_FiveDollarCount")),
            @AttributeOverride(name = "TwentyDollarCount", column = @Column(name = "MoneyInside_TwentyDollarCount"))
    })
    public Money MoneyInside = Money.None;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "OneCentCount", column = @Column(name = "MoneyInTransaction_OneCentCount")),
            @AttributeOverride(name = "TenCentCount", column = @Column(name = "MoneyInTransaction_TenCentCount")),
            @AttributeOverride(name = "QuarterCount", column = @Column(name = "MoneyInTransaction_QuarterCount")),
            @AttributeOverride(name = "OneDollarCount", column = @Column(name = "MoneyInTransaction_OneDollarCount")),
            @AttributeOverride(name = "FiveDollarCount", column = @Column(name = "MoneyInTransaction_FiveDollarCount")),
            @AttributeOverride(name = "TwentyDollarCount", column = @Column(name = "MoneyInTransaction_TwentyDollarCount"))
    })
    public Money MoneyInTransaction = Money.None;

    public Money getMoneyInside() {
        return MoneyInside;
    }

    public Money getMoneyInTransaction() {
        return MoneyInTransaction;
    }

    public void InsertMoney(Money money) {

        List<Money> coinsAndNotes = List.of(Money.Cent, Money.TenCent, Money.Quarter, Money.Dollar, Money.FiveDollar, Money.TwentyDollar);

        if (!coinsAndNotes.contains(money))
            throw new IllegalArgumentException();

        MoneyInTransaction = Money.Sum(money, MoneyInTransaction);
    }

    public void ReturnMoney() {
        MoneyInTransaction = Money.None;
    }

    public void BuySnack() {
        MoneyInside = Money.Sum(MoneyInTransaction, MoneyInside);
        MoneyInTransaction = Money.None;
    }
}
