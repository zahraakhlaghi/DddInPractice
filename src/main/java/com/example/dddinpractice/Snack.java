package com.example.dddinpractice;

import javax.persistence.*;
import java.util.List;

@Entity
public class Snack extends MyEntity{

    public String name;

    @OneToMany(mappedBy = "snackPie.snack", cascade = CascadeType.ALL)
    public List<Slot> slots;

    protected void setName(String name) {
        this.name = name;
    }

    public Snack() {

    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public String getName() {
        return name;
    }

    public Snack(String name) {
        this.name = name;
    }
}
