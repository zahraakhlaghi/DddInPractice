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

        Assertions.assertEquals(snackMachine.MoneyInTransaction.Amount(), 0.0);
    }

    @Test
    public void InsertedMoneyGoesToMoneyInTransaction() {

        SnackMachine snackMachine = new SnackMachine();

        snackMachine.InsertMoney(Money.Cent);
        snackMachine.InsertMoney(Money.Dollar);

        Assertions.assertEquals(snackMachine.MoneyInTransaction.Amount(), 1.01);
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
    public void MoneyInTransactionGoesToMoneyInsideAfterPurchase(){
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.InsertMoney(Money.Dollar);
        snackMachine.InsertMoney(Money.Dollar);

        snackMachine.BuySnack();

        Assertions.assertEquals(snackMachine.MoneyInTransaction, Money.None);
        Assertions.assertEquals(snackMachine.MoneyInside.Amount(), 2.0);
    }


}
