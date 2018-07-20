package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.exception.ValidationException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ValidationTest {

    private ValidationImpl validation;

    @BeforeClass
    public void setup() {
        validation = new ValidationImpl();
    }

    @Test(dataProviderClass = ValidationDataProvider.class, dataProvider = ValidationDataProvider.VALID_NUMBERS)
    public void testForIsValidNumber(String value) {
        Assert.assertTrue(validation.IsValidNumber.test(value));
    }

    @Test(dataProviderClass = ValidationDataProvider.class, dataProvider = ValidationDataProvider.INVALID_NUMBERS)
    public void testForIsValidNumberInvalid(String value) {
        Assert.assertFalse(validation.IsValidNumber.test(value));
    }

    @Test(dataProviderClass = ValidationDataProvider.class, dataProvider = ValidationDataProvider.VALID_PERCENTAGES)
    public void testForIsValidPercentage(String value) {
        Assert.assertTrue(validation.IsValidPercentage.test(value));
    }

    @Test(dataProviderClass = ValidationDataProvider.class, dataProvider = ValidationDataProvider.INVALID_PERCENTAGES)
    public void testForIsValidPercentageInvalid(String value) {
        Assert.assertFalse(validation.IsValidPercentage.test(value));
    }

    @Test(dataProviderClass = ValidationDataProvider.class, dataProvider = ValidationDataProvider.VALID_DURATIONS)
    public void testForIsValidDurationStr(String value) {
        Assert.assertTrue(validation.IsValidDurationStr.test(value));
    }

    @Test(dataProviderClass = ValidationDataProvider.class, dataProvider = ValidationDataProvider.INVALID_DURATIONS)
    public void testForIsValidDurationStrInvalid(String value) {
        Assert.assertFalse(validation.IsValidDurationStr.test(value));
    }

    @Test(dataProviderClass = ValidationDataProvider.class, dataProvider = ValidationDataProvider.VALID_LOANS)
    public void testValidateLoanAmount(String value) {
        Assert.assertTrue(validation.validateLoanAmount(value));
    }

    @Test(dataProviderClass = ValidationDataProvider.class, dataProvider = ValidationDataProvider.INVALID_LOANS)
    public void testValidateLoanAmountInvalid(String value) {
        Assert.assertFalse(validation.validateLoanAmount(value));
    }

    @Test(dataProviderClass = ValidationDataProvider.class, dataProvider = ValidationDataProvider.VALID_DURATIONS_I)
    public void testForIsValidDuration(String value) {
        Assert.assertTrue(validation.validateDuration(value));
    }

    @Test(dataProviderClass = ValidationDataProvider.class, dataProvider = ValidationDataProvider.INVALID_DURATIONS_I)
    public void testForIsValidDurationInvalid(String value) {
        Assert.assertFalse(validation.validateDuration(value));
    }

    @Test
    public void testValidate() throws ValidationException {
        CalculatorInput in = new CalculatorInput("100000", "10", "2.12", "2", "not validated");

        validation.validate(in);
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void testValidateFailure() throws ValidationException {
        CalculatorInput in = new CalculatorInput("100000", "10", "212", "2", "not validated");

        validation.validate(in);
    }
}