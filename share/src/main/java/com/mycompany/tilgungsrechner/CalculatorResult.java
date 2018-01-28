package com.mycompany.tilgungsrechner;

public class CalculatorResult {

    private final double value;

    CalculatorResult(final double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
