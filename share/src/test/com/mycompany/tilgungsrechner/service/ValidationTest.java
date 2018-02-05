package com.mycompany.tilgungsrechner.service;

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
    public void testForIsValidNumberReverse(String value) {
        Assert.assertFalse(validation.IsValidNumber.test(value));
    }
}