package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.data.CalculatorResult;
import com.mycompany.tilgungsrechner.exception.ValidationException;

import java.util.Random;

public class CalculatorImpl implements ICalculator {

    private final IValidation validation;

    CalculatorImpl() {
        this.validation = ServiceResolver.resolve(IValidation.class);
    }

    //todo equals und hashcode für listenresult!
    public CalculatorResult calculatePlan(CalculatorInput calculatorInput) throws ValidationException {
        validation.validate(calculatorInput);

        System.out.println(calculatorInput.toString());

        Random rnd = new Random();
        return new CalculatorResult(rnd.nextDouble());
    }
}
