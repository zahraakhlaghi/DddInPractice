package com.example.dddinpractice;


import com.example.dddinpractice.Atms.Atm;
import com.example.dddinpractice.SharedKernel.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AtmTest {

    @Test
    public void TakeMoneyExchangingMoneyWithCommission(){

        Atm atm = new Atm();
        atm.LoadMoney(Money.Dollar);

        atm.TakeMoney(1.0);

        Assertions.assertEquals(atm.getMoneyInside().Amount(),0.0);
        Assertions.assertEquals(atm.getMoneyCharged(),1.01);
    }

    @Test
    public void CommissionIsAtLeastOneCent(){
        Atm atm = new Atm();
        atm.LoadMoney(Money.Cent);

        atm.TakeMoney(0.01);

        Assertions.assertEquals(atm.getMoneyCharged(),0.02);
    }

    @Test
    public void CommissionIsRoundedUpToTheNextCent(){
        Atm atm = new Atm();
        atm.LoadMoney(Money.Sum(Money.Dollar,Money.TenCent));

        atm.TakeMoney(1.1);

        Assertions.assertEquals(atm.getMoneyCharged(),1.12);
    }
}
