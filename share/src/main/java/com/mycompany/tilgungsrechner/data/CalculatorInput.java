package com.mycompany.tilgungsrechner.data;

public class CalculatorInput {

    private final String loanAmount, durationOfDebit, fixedDebitRate, repaymentRate, payoutDateField;

    public CalculatorInput(final String loanAmount, final String durationOfDebit, final String fixedDebitRate,
                           final String repaymentRate, final String payoutDateField) {
        this.loanAmount = loanAmount;
        this.durationOfDebit = durationOfDebit;
        this.fixedDebitRate = fixedDebitRate;
        this.repaymentRate = repaymentRate;
        this.payoutDateField = payoutDateField;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public String getDurationOfDebit() {
        return durationOfDebit;
    }

    public String getFixedDebitRate() {
        return fixedDebitRate;
    }

    public String getRepaymentRate() {
        return repaymentRate;
    }

    public String getPayoutDateField() {
        return payoutDateField;
    }

    @Override
    public String toString() {
        return "CalculatorInput{" +
                "loanAmount='" + loanAmount + '\'' +
                ", durationOfDebit='" + durationOfDebit + '\'' +
                ", fixedDebitRate='" + fixedDebitRate + '\'' +
                ", repaymentRate='" + repaymentRate + '\'' +
                ", payoutDateField='" + payoutDateField + '\'' +
                '}';
    }
}
