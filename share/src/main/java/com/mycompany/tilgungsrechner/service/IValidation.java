package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.exception.ValidationException;

public interface IValidation {

    boolean validateLoanAmount(String value);
    boolean validatePercentage(String value);
    boolean validateDuration(String value);

    void validate(CalculatorInput input) throws ValidationException;
}