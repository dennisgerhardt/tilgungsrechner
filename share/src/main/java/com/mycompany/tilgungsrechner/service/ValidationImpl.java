package com.mycompany.tilgungsrechner.service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ValidationImpl implements IValidation {

    private static final Pattern VALID_NUMBER = Pattern.compile("^[0-9]+([.,]\\d{1,2})?$");

    final Predicate<String> IsValidNumber;
    private final Predicate<Double> IsValidLoanAmount;
    private final Predicate<Double> IsDoubleGreaterThanZero;

    ValidationImpl() {
        IsValidNumber = value -> VALID_NUMBER.matcher(value).matches();
        IsValidLoanAmount = value -> value >= 10000 && value <= 999999;
        IsDoubleGreaterThanZero = value -> value > 0;
    }

    public boolean validate(String value) {
        double d = Double.valueOf(value); //emptyString
        return true;//IsValidNumber.and(IsValidLoanAmount).and(IsDoubleGreaterThanZero).test(value);
    }
}