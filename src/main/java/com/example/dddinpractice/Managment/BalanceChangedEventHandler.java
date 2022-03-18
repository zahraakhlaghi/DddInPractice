package com.example.dddinpractice.Managment;

import com.example.dddinpractice.Atms.BalanceChangedEvent;
import com.example.dddinpractice.Common.IHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class BalanceChangedEventHandler implements IHandler<BalanceChangedEvent> {

    @Override
    public void Handle(BalanceChangedEvent domainEvent) {

        HeadOffice headOffice = HeadOfficeInstance.getInstance();
        headOffice.ChangeBalance(domainEvent.getDelta());
        //save change

    }
}
