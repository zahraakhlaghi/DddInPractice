package com.example.dddinpractice.Common;

public abstract class ValueObject<T> {

    protected abstract Boolean Equal(T other);

    protected abstract Integer GetHashCode();

    public Boolean Equals(ValueObject<T> a, ValueObject<T> b) {

        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;
        return a.equals(b);
    }

    public Boolean NotEquals(ValueObject<T> a, ValueObject<T> b) {
        return !Equals(a, b);
    }
}
