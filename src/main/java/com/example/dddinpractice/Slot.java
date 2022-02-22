package com.example.dddinpractice;

import javax.persistence.*;

@Entity
public class Slot extends MyEntity {


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "snack", column = @Column(name = "snack")),
            @AttributeOverride(name = "quantity", column = @Column(name = "quantity")),
            @AttributeOverride(name = "price", column = @Column(name = "price"))
    })
    private SnackPie snackPie;

    @ManyToOne(fetch = FetchType.EAGER)
    private SnackMachine snackMachine;
    private Integer position;

    public com.example.dddinpractice.SnackPie getSnackPie() {
        return snackPie;
    }

    protected void setSnackPie(com.example.dddinpractice.SnackPie snackPie) {
        this.snackPie = snackPie;
    }

    public Slot(SnackMachine snackMachine, Integer position) {
        this.snackMachine = snackMachine;
        this.position = position;
        snackPie = new SnackPie(null, 0, 0.0);
    }

    public Slot() {
    }

    public com.example.dddinpractice.SnackMachine getSnackMachine() {
        return snackMachine;
    }

    public Integer getPosition() {
        return position;
    }

    protected void setSnackMachine(com.example.dddinpractice.SnackMachine snackMachine) {
        this.snackMachine = snackMachine;
    }

    protected void setPosition(Integer position) {
        this.position = position;
    }
}
