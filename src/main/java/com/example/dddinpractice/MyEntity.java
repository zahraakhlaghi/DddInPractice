package com.example.dddinpractice;

import javax.persistence.*;
import java.util.Objects;


@MappedSuperclass
public abstract class MyEntity {

    @Id
    private long Id;

    public long getId() {
        return Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyEntity entity = (MyEntity) o;
        return Id == entity.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    public static Boolean Equals(Entity a, Entity b) {
        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;
        return a.equals(b);
    }

    public static Boolean NotEquals(Entity a, Entity b) {
        return !Equals(a, b);
    }

}
