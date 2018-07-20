package com.mycompany.tilgungsrechner.service;

import org.testng.annotations.DataProvider;

public class ValidationDataProvider {

    static final String VALID_NUMBERS = "valid.numbers";
    static final String VALID_PERCENTAGES = "valid.percentages";
    static final String VALID_DURATIONS = "valid.durations";
    static final String VALID_LOANS = "valid.loans";
    static final String VALID_DURATIONS_I = "valid.durations.i";

    static final String INVALID_NUMBERS = "invalid.numbers";
    static final String INVALID_PERCENTAGES = "invalid.percentages";
    static final String INVALID_DURATIONS = "invalid.durations";
    static final String INVALID_LOANS = "invalid.loans";
    static final String INVALID_DURATIONS_I = "invalid.durations.i";

    @DataProvider(name = VALID_NUMBERS)
    public static Object[][] validNumbers() {
        return new Object[][] {{"1"}, {"2"}, {"100"},  {"100000000"}, {"0.5"}, {"0.50"}, {"1.25"}, {"12.3"}, {"1234.56"} };
    }

    @DataProvider(name = INVALID_NUMBERS)
    public static Object[][] invalidNumbers() {
        return new Object[][] {{".1"}, {",23"}, {"1.234"},  {"100,"}, {"0,"} };
    }

    @DataProvider(name = VALID_PERCENTAGES)
    public static Object[][] validPercentages() {
        return new Object[][] {{"1"}, {"1.0"}, {"1.05"},  {"10.50"}};
    }

    @DataProvider(name = INVALID_PERCENTAGES)
    public static Object[][] invalidPercentages() {
        return new Object[][] {{".1"}, {".23"}, {"1.234"},  {"1."}, {"10."}, {"100"} };
    }

    @DataProvider(name = VALID_DURATIONS)
    public static Object[][] validDurations() {
        return new Object[][] {{"1"}, {"5"}, {"10"},  {"99"}};
    }

    @DataProvider(name = INVALID_DURATIONS)
    public static Object[][] invalidDurations() {
        return new Object[][] {{""}, {"100"} };
    }

    @DataProvider(name = VALID_LOANS)
    public static Object[][] validLoans() {
        return new Object[][] {{"10000"}, {"10000.0"}, {"10000.00"},  {"999999"}, {"1000000.00"}};
    }

    @DataProvider(name = INVALID_LOANS)
    public static Object[][] invalidLoans() {
        return new Object[][] {{"999"}, {"9999"}, {"9999.99"}, {"1000001"}, {"1000000.01"}, {"10000.001"} };
    }

    @DataProvider(name = VALID_DURATIONS_I)
    public static Object[][] validDurationsI() {
        return new Object[][] { {"5"}, {"10"},  {"30"}};
    }

    @DataProvider(name = INVALID_DURATIONS_I)
    public static Object[][] invalidDurationsI() {
        return new Object[][] {{"4"}, {"100"} };
    }
}