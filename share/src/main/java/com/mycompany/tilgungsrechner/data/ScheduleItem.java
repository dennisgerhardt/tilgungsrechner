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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleItem item = (ScheduleItem) o;

        if (debitAmount != null ? !debitAmount.equals(item.debitAmount) : item.debitAmount != null) return false;
        if (repaymentRate != null ? !repaymentRate.equals(item.repaymentRate) : item.repaymentRate != null)
            return false;
        if (debt != null ? !debt.equals(item.debt) : item.debt != null) return false;
        if (fixedRate != null ? !fixedRate.equals(item.fixedRate) : item.fixedRate != null) return false;
        return rateDate != null ? rateDate.equals(item.rateDate) : item.rateDate == null;
    }

    @Override
    public int hashCode() {
        int result = debitAmount != null ? debitAmount.hashCode() : 0;
        result = 31 * result + (repaymentRate != null ? repaymentRate.hashCode() : 0);
        result = 31 * result + (debt != null ? debt.hashCode() : 0);
        result = 31 * result + (fixedRate != null ? fixedRate.hashCode() : 0);
        result = 31 * result + (rateDate != null ? rateDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScheduleItem{" +
                "debitAmount=" + debitAmount +
                ", repaymentRate=" + repaymentRate +
                ", debt=" + debt +
                ", fixedRate=" + fixedRate +
                ", rateDate='" + rateDate + '\'' +
                '}';
    }
}