package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.data.CalculatorResult;

public interface ICalculator {

    CalculatorResult calculatePlan(CalculatorInput calculatorInput);
}
