package com.mycompany.tilgungsrechner.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public final class Money {

    private final Currency currency;
    private final BigDecimal amount;

    public Money(final Currency currency, final BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getFormatted() {
        return amount.setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP) + " " + currency.getSymbol();
    }

    public String getNegatedFormatted() {
        return amount.negate().setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP) + " " + currency.getSymbol();
    }


    @Override
    public String toString() {
        return "Money{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}