package com.mycompany.tilgungsrechner.service;

import org.testng.annotations.DataProvider;

public class ValidationDataProvider {

    static final String VALID_NUMBERS = "validNumbers";
    static final String INVALID_NUMBERS = "invalidNumbers";

    @DataProvider(name = VALID_NUMBERS)
    public static Object[][] validNumbers() {
        return new Object[][] {{"1"}, {"2"}, {"100"},  {"100000000"}, {"0.5"}, {"0,50"}, {"1,25"}, {"12,3"}, {"1234.56"} };
    }

    @DataProvider(name = INVALID_NUMBERS)
    public static Object[][] invalidNumbers() {
        return new Object[][] {{".1"}, {",23"}, {"1.234"},  {"100,"}, {"0,"} };
    }
}