package com.example.dddinpractice.Atms;

public class AtmDto {

    private Long id;
    private Double cash;

    public AtmDto(Long id, Double cash) {
        this.id = id;
        this.cash = cash;
    }

    public Long getId() {
        return id;
    }

    public Double getCash() {
        return cash;
    }
}
