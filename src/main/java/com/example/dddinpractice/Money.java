package com.example.dddinpractice;

import javax.persistence.Embeddable;

@Embeddable
public class Money extends ValueObject<Money> {

    public static final Money None = new Money(0, 0, 0, 0, 0, 0);
    public static final Money Cent = new Money(1, 0, 0, 0, 0, 0);
    public static final Money TenCent = new Money(0, 1, 0, 0, 0, 0);
    public static final Money Quarter = new Money(0, 0, 1, 0, 0, 0);
    public static final Money Dollar = new Money(0, 0, 0, 1, 0, 0);
    public static final Money FiveDollar = new Money(0, 0, 0, 0, 1, 0);
    public static final Money TwentyDollar = new Money(0, 0, 0, 0, 0, 1);

    protected Integer oneCentCount = 0;
    protected Integer tenCentCount = 0;
    protected Integer quarterCount = 0;
    protected Integer oneDollarCount = 0;
    protected Integer fiveDollarCount = 0;
    protected Integer twentyDollarCount = 0;

    public Money(Integer oneCentCount, Integer tenCentCount, Integer quarterCount, Integer oneDollarCount, Integer fiveDollarCount, Integer twentyDollarCount) {
        if (oneCentCount < 0 || tenCentCount < 0 || quarterCount < 0
                || oneDollarCount < 0 || fiveDollarCount < 0 || twentyDollarCount < 0)
            throw new IllegalArgumentException();
        this.oneCentCount = oneCentCount;
        this.tenCentCount = tenCentCount;
        this.quarterCount = quarterCount;
        this.oneDollarCount = oneDollarCount;
        this.fiveDollarCount = fiveDollarCount;
        this.twentyDollarCount = twentyDollarCount;
    }

    public Money() { }

    public static Money Sum(Money money1, Money money2) {

        return new Money(
                money1.oneCentCount + money2.oneCentCount,
                money1.tenCentCount + money2.tenCentCount,
                money1.quarterCount + money2.quarterCount,
                money1.oneDollarCount + money2.oneDollarCount,
                money1.fiveDollarCount + money2.fiveDollarCount,
                money1.twentyDollarCount + money2.twentyDollarCount
        );
    }

    public static Money Subtract(Money money1, Money money2) {

        return new Money(
                money1.oneCentCount - money2.oneCentCount,
                money1.tenCentCount - money2.tenCentCount,
                money1.quarterCount - money2.quarterCount,
                money1.oneDollarCount - money2.oneDollarCount,
                money1.fiveDollarCount - money2.fiveDollarCount,
                money1.twentyDollarCount - money2.twentyDollarCount
        );
    }

    public static Money Multiply(Money money, int multiplier) {

        return new Money(
                money.oneCentCount * multiplier,
                money.tenCentCount * multiplier,
                money.quarterCount * multiplier,
                money.oneDollarCount * multiplier,
                money.fiveDollarCount * multiplier,
                money.twentyDollarCount * multiplier
        );
    }

    public Double Amount() {
        return oneCentCount * 0.01 +
                tenCentCount * 0.10 +
                quarterCount * 0.25 +
                oneDollarCount +
                fiveDollarCount * 5 +
                twentyDollarCount * 20;
    }

    @Override
    protected Boolean Equal(Money other) {
        return this.oneCentCount.equals(other.oneCentCount)
                && this.tenCentCount.equals(other.tenCentCount)
                && this.quarterCount.equals(other.quarterCount)
                && this.oneDollarCount.equals(other.oneDollarCount)
                && this.fiveDollarCount.equals(other.fiveDollarCount)
                && this.twentyDollarCount.equals(other.twentyDollarCount);
    }

    @Override
    protected Integer GetHashCode() {
        Integer hashCode = oneCentCount;
        hashCode = (hashCode * 397) ^ tenCentCount;
        hashCode = (hashCode * 397) ^ quarterCount;
        hashCode = (hashCode * 397) ^ oneDollarCount;
        hashCode = (hashCode * 397) ^ fiveDollarCount;
        hashCode = (hashCode * 397) ^ twentyDollarCount;
        return hashCode;
    }

    @Override
    public String toString() {
        if (Amount() < 1)
            return "¢" + (Amount() * 100);
        return "$" + Amount().toString();
    }

    public Money Allocate(Double amount) {

        Integer twentyDollarCount = Math.min(this.twentyDollarCount, (amount.intValue() / 20));
        amount -= twentyDollarCount * 20;

        Integer fiveDollarCount = Math.min(this.fiveDollarCount, (amount.intValue() / 5));
        amount -= fiveDollarCount * 5;

        Integer oneDollarCount = Math.min(amount.intValue(), this.oneDollarCount);
        amount -= oneDollarCount;

        Integer quarterCount = Math.min(this.quarterCount, (int) (amount / 0.25));
        amount -= quarterCount * 0.25;

        Integer tenCentCount = Math.min(this.tenCentCount, (int) (amount / 0.10));
        amount -= tenCentCount * 0.10;

        Integer oneCentCount = Math.min(this.oneCentCount, (int) (amount / 0.01));

        return new Money(
                oneCentCount,
                tenCentCount,
                quarterCount,
                oneDollarCount,
                fiveDollarCount,
                twentyDollarCount
        );
    }
}
