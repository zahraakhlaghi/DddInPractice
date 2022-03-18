package com.example.dddinpractice.SnackMachines;

public class SnackMachineDto {

    private Long id;
    private Double moneyInside;

    public SnackMachineDto(Long id, Double moneyInside) {
        this.id = id;
        this.moneyInside = moneyInside;
    }

    public Long getId() {
        return id;
    }

    public Double getMoneyInside() {
        return moneyInside;
    }
}
