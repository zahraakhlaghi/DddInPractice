package com.example.dddinpractice.Common;

public interface IHandler<T extends IDomainEvent> {

    void Handle(T domainEvent);
}
