package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.data.Schedule;
import com.mycompany.tilgungsrechner.exception.ValidationException;

public interface IRepaymentSchedule {

    /**
     * creates an schedule for the given input
     * @param calculatorInput contains all needed values as strings
     * @return the {@link Schedule} which simply contains a list of {@linl com.mycompany.tilgungsrechner.data.ScheduleItem}
     * @throws ValidationException not handled in code
     */
    Schedule createSchedule(CalculatorInput calculatorInput) throws ValidationException;
}
