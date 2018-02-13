package com.mycompany.tilgungsrechner.data;

import java.util.List;

public final class Schedule {

    private final List<ScheduleItem> scheduleItems;

    public Schedule(List<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }

    public List<ScheduleItem> getScheduleItems() {
        return scheduleItems;
    }
}
