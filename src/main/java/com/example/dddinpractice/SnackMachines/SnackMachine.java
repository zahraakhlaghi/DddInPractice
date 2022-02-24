package com.example.dddinpractice.SnackMachines;

import com.example.dddinpractice.Common.MyEntity;
import com.example.dddinpractice.SharedKernel.Money;

import javax.persistence.*;
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
    private Money moneyInside;
    private Double moneyInTransaction;

    @OneToMany(mappedBy = "snackMachine", cascade = CascadeType.ALL)
    private List<Slot> Slots;

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
        moneyInside = Money.None;
        moneyInTransaction = 0.0;
        Slots = new ArrayList<>();
        Slots.add(new Slot(this, 1));
        Slots.add(new Slot(this, 2));
        Slots.add(new Slot(this, 3));
        Slots.add(new Slot(this, 4));
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

    public String CanBuySnack(Integer position) {
        SnackPie snackPie = GetSnackPie(position);

        if (snackPie.quantity == 0)
            return "The snack pile is empty";
        if (moneyInTransaction < snackPie.price)
            return "Not enough money";
        if (!moneyInside.CanAllocate(moneyInTransaction - snackPie.price))
            return "Not enough change";
        return null;
    }

    public void BuySnack(Integer position) {

        if (CanBuySnack(position) != null)
            throw new IllegalArgumentException(CanBuySnack(position));

        Slot slot = GetSlot(position);
        slot.setSnackPie(slot.getSnackPie().SubtractOne());
        Money change = moneyInside.Allocate(getMoneyInTransaction() - slot.getSnackPie().price);
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
