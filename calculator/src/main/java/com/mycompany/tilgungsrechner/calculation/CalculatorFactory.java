package com.mycompany.tilgungsrechner.calculation;

import com.mycompany.tilgungsrechner.data.CalculatorInput;

public class CalculatorFactory {

    public static Calculator AnnuityLoan(CalculatorInput calculatorInput) {
        return new AnnuityLoanCalculator(calculatorInput);
    }
}