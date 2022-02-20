package com.example.dddinpractice;

import javax.persistence.Embeddable;
import java.security.InvalidParameterException;

@Embeddable
public class Money extends ValueObject<Money> {

    public static final Money None = new Money(0, 0, 0, 0, 0, 0);
    public static final Money Cent = new Money(1, 0, 0, 0, 0, 0);
    public static final Money TenCent = new Money(0, 1, 0, 0, 0, 0);
    public static final Money Quarter = new Money(0, 0, 1, 0, 0, 0);
    public static final Money Dollar = new Money(0, 0, 0, 1, 0, 0);
    public static final Money FiveDollar = new Money(0, 0, 0, 0, 1, 0);
    public static final Money TwentyDollar = new Money(0, 0, 0, 0, 0, 1);

    public Integer OneCentCount;
    public Integer TenCentCount;
    public Integer QuarterCount;
    public Integer OneDollarCount;
    public Integer FiveDollarCount;
    public Integer TwentyDollarCount;

    public Money(Integer oneCentCount, Integer tenCentCount, Integer quarterCount, Integer oneDollarCount, Integer fiveDollarCount, Integer twentyDollarCount) {
        if (oneCentCount < 0 || tenCentCount < 0 || quarterCount < 0
                || oneDollarCount < 0 || fiveDollarCount < 0 || twentyDollarCount < 0)
            throw new IllegalArgumentException();
        OneCentCount = oneCentCount;
        TenCentCount = tenCentCount;
        QuarterCount = quarterCount;
        OneDollarCount = oneDollarCount;
        FiveDollarCount = fiveDollarCount;
        TwentyDollarCount = twentyDollarCount;
    }

    public Money() {

    }

    public static Money Sum(Money money1, Money money2) {

        return new Money(
                money1.OneCentCount + money2.OneCentCount,
                money1.TenCentCount + money2.TenCentCount,
                money1.QuarterCount + money2.QuarterCount,
                money1.OneDollarCount + money2.OneDollarCount,
                money1.FiveDollarCount + money2.FiveDollarCount,
                money1.TwentyDollarCount + money2.TwentyDollarCount
        );
    }

    public static Money Subtract(Money money1, Money money2) {

        return new Money(
                money1.OneCentCount - money2.OneCentCount,
                money1.TenCentCount - money2.TenCentCount,
                money1.QuarterCount - money2.QuarterCount,
                money1.OneDollarCount - money2.OneDollarCount,
                money1.FiveDollarCount - money2.FiveDollarCount,
                money1.TwentyDollarCount - money2.TwentyDollarCount
        );
    }

    public Double Amount() {
        return OneCentCount * 0.01 +
                TenCentCount * 0.10 +
                QuarterCount * 0.25 +
                OneDollarCount +
                FiveDollarCount * 5 +
                TwentyDollarCount * 20;
    }

    @Override
    protected Boolean Equal(Money other) {
        return this.OneCentCount.equals(other.OneCentCount)
                && this.TenCentCount.equals(other.TenCentCount)
                && this.QuarterCount.equals(other.QuarterCount)
                && this.OneDollarCount.equals(other.OneDollarCount)
                && this.FiveDollarCount.equals(other.FiveDollarCount)
                && this.TwentyDollarCount.equals(other.TwentyDollarCount);
    }

    @Override
    protected Integer GetHashCode() {
        Integer hashCode = OneCentCount;
        hashCode = (hashCode * 397) ^ TenCentCount;
        hashCode = (hashCode * 397) ^ QuarterCount;
        hashCode = (hashCode * 397) ^ OneDollarCount;
        hashCode = (hashCode * 397) ^ FiveDollarCount;
        hashCode = (hashCode * 397) ^ TwentyDollarCount;
        return hashCode;
    }
}
