package com.mycompany.tilgungsrechner;

public class CalculatorImpl implements ICalculator {

    public CalculatorResult calculatePlan(CalculatorInput calculatorInput) {
        return new CalculatorResult(2.5d);
    }
}
