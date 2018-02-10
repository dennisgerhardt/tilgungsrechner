package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.data.CalculatorResult;
import com.mycompany.tilgungsrechner.exception.ValidationException;

public interface ICalculator {

    CalculatorResult calculatePlan(CalculatorInput calculatorInput) throws ValidationException;
}
