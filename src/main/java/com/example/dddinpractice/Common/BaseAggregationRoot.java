package com.example.dddinpractice.Common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public abstract class BaseAggregationRoot extends BaseEntity {
    private final List<Object> events = new ArrayList<>();

    protected <T> T registerEvent(T event) {
        events.add(event);
        return event;
    }

    public void clearEvents(){
        events.clear();
    }

    public Collection<Object> events() {
        return Collections.unmodifiableList(events);
    }
}
