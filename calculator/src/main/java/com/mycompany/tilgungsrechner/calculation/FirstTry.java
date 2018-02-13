package com.mycompany.tilgungsrechner.calculation;

import java.time.Month;

/**
 * not in use
 */
final class FirstTry {

    private FirstTry(){}

    private void setup() {
        final double amount = 100000d;
        final double sollZins = 2.12d;
        final double tilgung = 2.0d;
        final int jahre = 10;

        double firstDebitRate = (amount * (sollZins / 100)) / 12 ;
        double firstRepayment = (amount * (tilgung / 100)) / 12 ;

        double rate = (round (
                firstDebitRate  + firstRepayment
        ));

        calc(amount, rate, sollZins, jahre);
    }

    private void calc(double amount, double rate, double sollZins, int jahre) {
        final int months = Month.values().length;
        final int totalMonths = jahre * months;

        double zinsPerMonth, tilgungPerMonth;
        for (int i = totalMonths; i > 0; i--) {
            zinsPerMonth = round( (amount * (sollZins / 100)) / months );
            tilgungPerMonth = round(rate - zinsPerMonth);
            amount = round (amount - tilgungPerMonth);

            System.out.println(amount + " --- " + zinsPerMonth + " --- " + tilgungPerMonth);
        }
    }

    private double round(double a) {
        return Math.round(a * 100.0) / 100.0;
    }
}