package com.example.dddinpractice.Atms;

import com.example.dddinpractice.Common.IDomainEvent;

public class BalanceChangedEvent implements IDomainEvent {
    
    private Double delta;

    public Double getDelta() {
        return delta;
    }

    public BalanceChangedEvent(Double delta) {
        this.delta = delta;
    }
}
