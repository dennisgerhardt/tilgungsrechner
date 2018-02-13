package com.mycompany.tilgungsrechner.util;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public final class DateUtil {

    public static LocalDate lastDayOfMonth(final int plusMonth) {
        LocalDate currentDayOfMonth = LocalDate.now();
        LocalDate lastDayOfMonth = currentDayOfMonth.plusMonths(plusMonth).with(TemporalAdjusters.lastDayOfMonth());

        return lastDayOfMonth;
    }
}
