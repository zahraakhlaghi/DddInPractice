package com.example.dddinpractice;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SnackMachine extends MyEntity {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "oneCentCount", column = @Column(name = "moneyInside_OneCentCount")),
            @AttributeOverride(name = "tenCentCount", column = @Column(name = "moneyInside_TenCentCount")),
            @AttributeOverride(name = "quarterCount", column = @Column(name = "moneyInside_QuarterCount")),
            @AttributeOverride(name = "oneDollarCount", column = @Column(name = "moneyInside_OneDollarCount")),
            @AttributeOverride(name = "fiveDollarCount", column = @Column(name = "moneyInside_FiveDollarCount")),
            @AttributeOverride(name = "twentyDollarCount", column = @Column(name = "moneyInside_TwentyDollarCount"))
    })
    private Money moneyInside = Money.None;
    private Double moneyInTransaction = 0.0;

    @OneToMany(mappedBy = "snackMachine", cascade = CascadeType.ALL)
    private List<Slot> Slots = List.of(new Slot(this, 1), new Slot(this, 2), new Slot(this, 3), new Slot(this, 4));


    public Money getMoneyInside() {
        return moneyInside;
    }

    public Double getMoneyInTransaction() {
        return moneyInTransaction;
    }

    public List<Slot> getSlots() {
        return Slots;
    }

    public SnackMachine() {
//        moneyInside = ;
//        moneyInTransaction = 0.0;
//        Slots = new ArrayList<>();
//        Slots.add();
//        Slots.add(new Slot(this, 2));
//        Slots.add(new Slot(this, 3));
//        Slots.add(new Slot(this, 4));
    }

    public void InsertMoney(Money money) {

        List<Money> coinsAndNotes = List.of(Money.Cent, Money.TenCent, Money.Quarter, Money.Dollar, Money.FiveDollar, Money.TwentyDollar);

        if (!coinsAndNotes.contains(money))
            throw new IllegalArgumentException();

        moneyInTransaction += money.Amount();
        moneyInside = Money.Sum(money, moneyInside);
    }

    public void ReturnMoney() {

        Money moneyToReturn = moneyInside.Allocate(moneyInTransaction);
        moneyInside = Money.Subtract(moneyInside, moneyToReturn);
        moneyInTransaction = 0.0;
    }

    public void BuySnack(Integer position) {
        Slot slot = GetSlot(position);
        if (slot.getSnackPie().price > moneyInTransaction)
            throw new IllegalArgumentException();

        slot.setSnackPie(slot.getSnackPie().SubtractOne());

        Money change = moneyInside.Allocate(getMoneyInTransaction() - slot.getSnackPie().price);

        if (change.Amount() < (getMoneyInTransaction() - slot.getSnackPie().price))
            throw new InvalidParameterException();

        moneyInside = Money.Subtract(moneyInside, change);
        moneyInTransaction = 0.0;
    }

    public SnackPie GetSnackPie(Integer position) {
        return GetSlot(position).getSnackPie();
    }

    public Slot GetSlot(Integer position) {
        return Slots.stream()
                .filter(x -> x.getPosition().equals(position))
                .findFirst()
                .orElse(null);
    }

    public void LoadSnack(Integer position, SnackPie snackPie) {
        Slot slot = GetSlot(position);
        slot.setSnackPie(snackPie);
    }

    public void LoadMoney(Money money) {
        moneyInside = Money.Sum(money, moneyInside);
    }
}
