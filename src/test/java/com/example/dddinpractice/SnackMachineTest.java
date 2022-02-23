package com.example.dddinpractice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SnackMachineTest {

    @Test
    public void ReturnMoneyEmptiesMoneyInTransaction() {

        SnackMachine snackMachine = new SnackMachine();
        snackMachine.InsertMoney(Money.Dollar);

        snackMachine.ReturnMoney();

        Assertions.assertEquals(snackMachine.getMoneyInTransaction(), 0.0);
    }

    @Test
    public void InsertedMoneyGoesToMoneyInTransaction() {

        SnackMachine snackMachine = new SnackMachine();

        snackMachine.InsertMoney(Money.Cent);
        snackMachine.InsertMoney(Money.Dollar);

        Assertions.assertEquals(snackMachine.getMoneyInTransaction(), 1.01);
    }

    @Test
    public void CannotInsertMoreThanOneCoinOrNNoteAtATime() {

        SnackMachine snackMachine = new SnackMachine();
        Money twoCent = Money.Sum(Money.Cent, Money.Cent);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    snackMachine.InsertMoney(twoCent);
                });
    }

    @Test
    public void BuySnackTradesInsertedMoneyForASnack() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.LoadSnack(1, new SnackPie(Snack.Chocolate, 10, 1.0));
        snackMachine.InsertMoney(Money.Dollar);

        snackMachine.BuySnack(1);

        Assertions.assertEquals(snackMachine.getMoneyInTransaction(), 0.0);
        Assertions.assertEquals(snackMachine.getMoneyInside().Amount(), 1.0);
        Assertions.assertEquals(snackMachine.GetSnackPie(1).quantity, 9);
    }

    @Test
    public void CannotMakePurchaseWhenThereIsNoSnacks() {

        SnackMachine snackMachine = new SnackMachine();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    snackMachine.BuySnack(1);
                });
    }

    @Test
    public void CannotMakePurchaseIfNotEnoughMoneyInserted() {

        SnackMachine snackMachine = new SnackMachine();
        snackMachine.LoadSnack(1, new SnackPie(Snack.Chocolate, 1, 2.0));
        snackMachine.InsertMoney(Money.Dollar);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    snackMachine.BuySnack(1);
                });
    }

    @Test
    public void SnackMachineReturnsMoneyWithHighestDenominationFirst() {

        SnackMachine snackMachine = new SnackMachine();
        snackMachine.LoadMoney(Money.Dollar);

        snackMachine.InsertMoney(Money.Quarter);
        snackMachine.InsertMoney(Money.Quarter);
        snackMachine.InsertMoney(Money.Quarter);
        snackMachine.InsertMoney(Money.Quarter);
        snackMachine.ReturnMoney();


        Assertions.assertEquals(snackMachine.getMoneyInside().quarterCount, 4);
        Assertions.assertEquals(snackMachine.getMoneyInside().oneDollarCount, 0);
    }

    @Test
    public void AfterPurchaseChangeIsReturned() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.LoadSnack(1, new SnackPie(Snack.Chocolate, 1, 0.5));
        snackMachine.LoadMoney(Money.Multiply(Money.TenCent, 10));

        snackMachine.InsertMoney(Money.Dollar);
        snackMachine.BuySnack(1);

        Assertions.assertEquals(snackMachine.getMoneyInside().Amount(), 1.5);
        Assertions.assertEquals(snackMachine.getMoneyInTransaction(), 0);

    }

    @Test
    public void CannotBuySnackIfNotEnoughChange() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.LoadSnack(1, new SnackPie(Snack.Chocolate, 1, 0.5));
        snackMachine.InsertMoney(Money.Dollar);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    snackMachine.BuySnack(1);
                });

    }

}
