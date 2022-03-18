package com.example.dddinpractice.SnackMachines;

import com.example.dddinpractice.Common.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Snack extends BaseEntity {

    public static final Snack None = new Snack(1, "None");
    public static final Snack Chocolate = new Snack(1, "Chocolate");
    public static final Snack Soda = new Snack(1, "Soda");
    public static final Snack Gum = new Snack(1, "Gum");


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

    public Snack(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
