package com.example.dddinpractice.Common;

import javax.persistence.*;
import java.util.Objects;


@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected long id;


    protected void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity entity = (BaseEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
