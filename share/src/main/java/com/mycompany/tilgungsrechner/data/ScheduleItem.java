package com.mycompany.tilgungsrechner.data;

import java.math.BigDecimal;
import java.util.Currency;

public final class ScheduleItem {

    private final Money debitAmount, repaymentRate, debt, fixedRate;
    private String rateDate;

    //2,3,14
    public ScheduleItem(BigDecimal debt, BigDecimal debitAmount, BigDecimal repaymentRate, BigDecimal fixedRate) {
        this.debt = new Money(Currency.getInstance("EUR"), debt);
        this.debitAmount = new Money(Currency.getInstance("EUR"), debitAmount);
        this.repaymentRate = new Money(Currency.getInstance("EUR"), repaymentRate);
        this.fixedRate = new Money(Currency.getInstance("EUR"), fixedRate);
    }

    public String getDebitAmount() {
        return debitAmount.getFormatted();
    }

    public String getRepaymentRate() {
        return repaymentRate.getFormatted();
    }

    public String getDebt() {
        return debt.getNegatedFormatted();
    }

    public String getFixedRate() {
        return fixedRate.getFormatted();
    }

    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(final String rateDate) {
        this.rateDate = rateDate;
    }
}