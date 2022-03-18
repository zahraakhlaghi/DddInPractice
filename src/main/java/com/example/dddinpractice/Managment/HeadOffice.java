package com.example.dddinpractice.Managment;

import com.example.dddinpractice.Atms.Atm;
import com.example.dddinpractice.Common.BaseAggregationRoot;
import com.example.dddinpractice.Common.BaseEntity;
import com.example.dddinpractice.SharedKernel.Money;
import com.example.dddinpractice.SnackMachines.SnackMachine;
import lombok.Getter;

import javax.persistence.Entity;

@Entity
public class HeadOffice extends BaseAggregationRoot {

    private Double balance = 0.0;
    private Money cash = Money.None;


    public void ChangeBalance(Double delta) {
        balance += delta;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void UnloadCashFromSnackMachine(SnackMachine snackMachine) {

        Money money = snackMachine.UnLoadMoney();
        cash = Money.Sum(cash, money);
    }

    public void LoadCashToAtm(Atm atm){
        atm.LoadMoney(cash);
        cash = Money.None;
    }

    public HeadOffice() {
    }

    public Double getBalance() {
        return balance;
    }

    public Money getCash() {
        return cash;
    }


}
