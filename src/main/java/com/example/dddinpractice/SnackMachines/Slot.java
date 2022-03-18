package com.example.dddinpractice.SnackMachines;

import com.example.dddinpractice.Common.BaseEntity;

import javax.persistence.*;

@Entity
public class Slot extends BaseEntity {


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

    public SnackPie getSnackPie() {
        return snackPie;
    }

    protected void setSnackPie(SnackPie snackPie) {
        this.snackPie = snackPie;
    }

    public Slot(SnackMachine snackMachine, Integer position) {
        this.snackMachine = snackMachine;
        this.position = position;
        snackPie = SnackPie.Empty;
    }

    public Slot() {
    }

    public SnackMachine getSnackMachine() {
        return snackMachine;
    }

    public Integer getPosition() {
        return position;
    }

    protected void setSnackMachine(SnackMachine snackMachine) {
        this.snackMachine = snackMachine;
    }

    protected void setPosition(Integer position) {
        this.position = position;
    }
}
