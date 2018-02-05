package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.data.CalculatorResult;

import java.util.Random;

public class CalculatorImpl implements ICalculator {

    CalculatorImpl() {
    }

    public CalculatorResult calculatePlan(CalculatorInput calculatorInput) {
        Random rnd = new Random();
        return new CalculatorResult(rnd.nextDouble());
    }
}
