package com.example.dddinpractice;

import com.example.dddinpractice.SharedKernel.Money;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@RunWith(JUnitParamsRunner.class)
public class MoneyTest {

    @Test
    public void SumOfTwoMoneysProductsCorrectResult() {

        Money money1 = new Money(1, 2, 3, 4, 5, 6);
        Money money2 = new Money(1, 2, 3, 4, 5, 6);

        Money sum = Money.Sum(money1, money2);

        Assertions.assertEquals(sum.getOneCentCount(), 2);
        Assertions.assertEquals(sum.getTenCentCount(), 4);
        Assertions.assertEquals(sum.getQuarterCount(), 6);
        Assertions.assertEquals(sum.getOneDollarCount(), 8);
        Assertions.assertEquals(sum.getFiveDollarCount(), 10);
        Assertions.assertEquals(sum.getTwentyDollarCount(), 12);

    }

    @Test
    public void TwoMoneysInstancesEqualIfContainTheSameMoneyAmount() {

        Money money1 = new Money(1, 2, 3, 4, 5, 6);
        Money money2 = new Money(1, 2, 3, 4, 5, 6);

        Assertions.assertTrue(money1.Equal(money2));
        Assertions.assertEquals(money1.GetHashCode(), money2.GetHashCode());

    }

    @Test
    public void TwoMoneysInstancesDoNotEqualIfContainDifferentMoneyAmount() {

        Money money1 = new Money(0, 2, 3, 0, 0, 0);
        Money money2 = new Money(1, 2, 3, 4, 5, 6);

        Assertions.assertFalse(money1.Equal(money2));
        Assertions.assertNotEquals(money1.GetHashCode(), money2.GetHashCode());

    }


    @Test
    @Parameters({
            "-1, 0, 0, 0, 0, 0",
            "0, -1, 0, 0, 0, 0",
            "0, 0, -1, 0, 0, 0",
            "0, 0, 0, -1, 0, 0",
            "0, 0, 0, 0, -1, 0",
            "0, 0, 0, 0, 0, -1"})
    public void CannotCreateMoneyWithNegativeValue(
            Integer oneCentCount,
            Integer tenCentCount,
            Integer quarterCount,
            Integer oneDollarCount,
            Integer fiveDollarCount,
            Integer twentyDollarCount) {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new Money(
                            oneCentCount,
                            tenCentCount,
                            quarterCount,
                            oneDollarCount,
                            fiveDollarCount,
                            twentyDollarCount
                    );
                });
    }

    @Test
    @Parameters({
            "1, 2, 0, 0, 0, 0, 0.21",
            "1, 2, 3, 0, 0, 0, 0.96",
            "1, 2, 3, 4, 0, 0, 4.96",
            "1, 2, 3, 4, 5, 0, 29.96",
            "1, 2, 3, 4, 5, 6, 149.96",
            "11, 0, 0, 0, 0, 0, 0.11"})
    public void AmountIsCalculatedCorrectly(
            Integer oneCentCount,
            Integer tenCentCount,
            Integer quarterCount,
            Integer oneDollarCount,
            Integer fiveDollarCount,
            Integer twentyDollarCount,
            Double expectedAmount) {

        Money money = new Money(oneCentCount, tenCentCount, quarterCount, oneDollarCount, fiveDollarCount, twentyDollarCount);

        Assertions.assertEquals(money.Amount(), expectedAmount);
    }

    @Test
    public void SubtractionOfTwoMoneysProducesCorrectResult() {

        Money money1 = new Money(10, 10, 10, 10, 10, 10);
        Money money2 = new Money(1, 2, 3, 4, 5, 6);

        Money result = Money.Subtract(money1, money2);

        Assertions.assertEquals(result.getOneCentCount(), 9);
        Assertions.assertEquals(result.getTenCentCount(), 8);
        Assertions.assertEquals(result.getQuarterCount(), 7);
        Assertions.assertEquals(result.getOneDollarCount(), 6);
        Assertions.assertEquals(result.getFiveDollarCount(), 5);
        Assertions.assertEquals(result.getTwentyDollarCount(), 4);


    }

    @Test
    public void CannotSubtractMoreThanExists() {

        Money money1 = new Money(0, 1, 0, 0, 0, 0);
        Money money2 = new Money(1, 0, 0, 0, 0, 0);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Money.Subtract(money1, money2);
                });
    }

    @Test
    @Parameters({
            "1, 0, 0, 0, 0, 0, ¢1.0",
            "0, 1, 0, 0, 0, 0, ¢10.0",
            "1, 0, 0, 1, 0, 0, $1.01",
            "0, 0, 2, 1, 0, 0, $1.5"})
    public void ToStringShouldReturnAmountOfMoney(
            Integer oneCentCount,
            Integer tenCentCount,
            Integer quarterCount,
            Integer oneDollarCount,
            Integer fiveDollarCount,
            Integer twentyDollarCount,
            Double expectedString) {

        Money money = new Money(oneCentCount, tenCentCount, quarterCount, oneDollarCount, fiveDollarCount, twentyDollarCount);


        Assertions.assertEquals(money.toString(), expectedString);

    }


}
