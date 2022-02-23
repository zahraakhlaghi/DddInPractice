package com.example.dddinpractice;

import javax.persistence.*;
import java.math.BigDecimal;
import java.security.InvalidParameterException;

@Embeddable
public class SnackPie extends ValueObject<SnackPie> {

    public static final SnackPie Empty = new SnackPie(Snack.None, 0, 0.0);

    @ManyToOne(fetch = FetchType.EAGER)
    protected Snack snack;
    protected Integer quantity;
    protected Double price;


    public SnackPie SubtractOne() {
        return new SnackPie(snack, quantity - 1, price);
    }


    public SnackPie() {
    }

    public SnackPie(Snack snack, Integer quantity, Double price) {
        if (quantity < 0)
            throw new IllegalArgumentException();
        if (price < 0)
            throw new IllegalArgumentException();
        if (((int) (price / 0.1)) * 0.1 != price)
            throw new IllegalArgumentException();

        this.snack = snack;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    protected Boolean Equal(SnackPie other) {
        return snack == other.snack &&
                quantity == other.quantity &&
                price == other.price;
    }

    @Override
    protected Integer GetHashCode() {
        Integer hashCode = snack.hashCode();
        hashCode = (hashCode * 397) ^ quantity;
        hashCode = (hashCode * 397) ^ price.hashCode();
        return hashCode;
    }


}
