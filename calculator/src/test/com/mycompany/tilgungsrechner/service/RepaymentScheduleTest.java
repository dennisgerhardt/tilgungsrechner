package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.data.Schedule;
import com.mycompany.tilgungsrechner.data.ScheduleItem;
import com.mycompany.tilgungsrechner.exception.ValidationException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RepaymentScheduleTest {

    private final int SCHEDULE_SIZE = 1 + 120 + 1;

    private IRepaymentSchedule scheduler;
    private CalculatorInput input;

    @BeforeClass
    public void setup() {
        input = new CalculatorInput("100000.00", "10", "2.12", "2.0", "2018-02-28");
        scheduler = new RepaymentScheduleImpl();
    }

    @Test
    public void testCreateScheduleSize() throws ValidationException {
        final Schedule schedule = scheduler.createSchedule(input);

        Assert.assertEquals(schedule.getScheduleItems().size(), SCHEDULE_SIZE);
    }

    @Test
    public void testCreateScheduleNotNull() throws ValidationException {
        final Schedule schedule = scheduler.createSchedule(input);

        final long count = schedule.getScheduleItems().stream().filter(item ->
                item.getDebitAmount() != null &&
                        item.getDebt() != null &&
                        item.getFixedRate() != null &&
                        item.getRepaymentRate() != null &&
                        item.getRateDate() != null
        ).count();

        Assert.assertTrue(count == SCHEDULE_SIZE);
    }

    @Test
    public void testCreateScheduleOverview() throws ValidationException {
        final Schedule schedule = scheduler.createSchedule(input);

        final ScheduleItem item = schedule.getScheduleItems().get(SCHEDULE_SIZE - 1);

        Assert.assertEquals(item.getDebt(), "-77.744,14 €");
        Assert.assertEquals(item.getDebitAmount(), "18.943,74 €");
        Assert.assertEquals(item.getRepaymentRate(), "22.255,86 €");
        Assert.assertEquals(item.getFixedRate(), "41.199,60 €");
    }
}