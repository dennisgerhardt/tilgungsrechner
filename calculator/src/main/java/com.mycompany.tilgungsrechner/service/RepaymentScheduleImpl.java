package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.calculation.Calculator;
import com.mycompany.tilgungsrechner.calculation.CalculatorFactory;
import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.data.Schedule;
import com.mycompany.tilgungsrechner.data.ScheduleItem;
import com.mycompany.tilgungsrechner.exception.ValidationException;
import com.mycompany.tilgungsrechner.util.DateUtil;

import java.math.BigDecimal;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Optional;

public class RepaymentScheduleImpl implements IRepaymentSchedule {

    //private final IValidation validation;

    RepaymentScheduleImpl() {
        //this.validation = ServiceResolver.resolve(IValidation.class);
    }

    public Schedule createSchedule(CalculatorInput calculatorInput) throws ValidationException {
        //validation.validate(calculatorInput);

        final int totalMonths = Integer.valueOf(calculatorInput.getDurationOfDebit()) * Month.values().length;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy");

        Calculator calculator = CalculatorFactory.AnnuityLoan(calculatorInput);

        LinkedList<ScheduleItem> scheduleItems = new LinkedList<>();
        for (int i = 0; i <= totalMonths && !calculator.earlyTerminated(); i++) {
            ScheduleItem item = calculator.calculate(Optional.of(i < totalMonths));

            if (item.getRateDate() == null) {
                item.setRateDate(DateUtil.lastDayOfMonth(i + 1).format(formatter));
            }
            scheduleItems.add(item);
        }

        ScheduleItem first = new ScheduleItem(new BigDecimal(calculatorInput.getLoanAmount()),
                new BigDecimal("0.00"),
                new BigDecimal(calculatorInput.getLoanAmount()).negate(),
                new BigDecimal(calculatorInput.getLoanAmount()).negate());
        first.setRateDate(DateUtil.lastDayOfMonth(0).format(formatter));
        scheduleItems.addFirst(first);

        return new Schedule(scheduleItems);
    }
}
