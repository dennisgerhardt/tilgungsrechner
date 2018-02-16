package com.mycompany.tilgungsrechner.calculation;

import com.mycompany.tilgungsrechner.data.ScheduleItem;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Month;
import java.util.Optional;

public abstract class Calculator<T> {

    private static final int INTERNAL_PRECISION = 8;
    static final int PRECISION = 2;

    static BigDecimal Twelve = new BigDecimal(String.valueOf(Month.values().length));
    static BigDecimal OneHundred = new BigDecimal("100");

    static MathContext InternalRounding = new MathContext(INTERNAL_PRECISION, RoundingMode.HALF_UP);

    /**
     * all values are monthly results
     * @return a {@link ScheduleItem} with all values as money objects
     */
    public abstract ScheduleItem calculate(Optional<T> optionalParameter);

    /**
     * If wanted, here you can get a summary for the schedule,
     * but you have to call the {@link Calculator#calculate(Optional)} first.
     * It's optional.
     * @return a {@link ScheduleItem} with all values as money objects
     */
    public ScheduleItem overview() {
        throw new UnsupportedOperationException();
    }

    public boolean earlyTerminated() {
        return false;
    }

    abstract BigDecimal calculateDebitAmount();

    abstract BigDecimal calculateRepaymentRate();

    abstract BigDecimal calculateRemainingDebt();
}