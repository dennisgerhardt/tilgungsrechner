package com.mycompany.tilgungsrechner.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public final class Money {

    private static final NumberFormat FORMAT = NumberFormat.getCurrencyInstance(new Locale("de", "DE"));

    private final Currency currency;
    private final BigDecimal amount;

    public Money(final Currency currency, final BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getFormatted() {
        return FORMAT.format(amount.setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP).doubleValue());
    }

    public String getNegatedFormatted() {
        return FORMAT.format(amount.negate().setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP).doubleValue());
    }


    @Override
    public String toString() {
        return "Money{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}