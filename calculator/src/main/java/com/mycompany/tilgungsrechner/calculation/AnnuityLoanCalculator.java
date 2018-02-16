package com.mycompany.tilgungsrechner.calculation;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.data.ScheduleItem;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Optional;

final class AnnuityLoanCalculator extends Calculator<Boolean> {

    private static final BigDecimal MONTHS_COUNT = new BigDecimal(String.valueOf(Month.values().length));

    private final BigDecimal debitRate;
    private final BigDecimal fixedRate;

    private BigDecimal debitAmount, repaymentRate, debt;
    private BigDecimal sumDebitAmount, sumRepaymentRate;
    private boolean earlyTerminated;

    AnnuityLoanCalculator(CalculatorInput in) {
        debt = new BigDecimal(in.getLoanAmount());
        debitRate = new BigDecimal(in.getFixedDebitRate()).divide(Calculator.OneHundred, Calculator.InternalRounding);

        fixedRate = calculateFixedRate(debt, debitRate, in.getRepaymentRate());
        sumDebitAmount = BigDecimal.ZERO;
        sumRepaymentRate = BigDecimal.ZERO;
    }

    @Override
    public ScheduleItem calculate(Optional<Boolean> optionalParameter) {
        if (!optionalParameter.get() || isEarlyTerminated()) {
            return overview();
        } else {
            BigDecimal debitAmount = calculateDebitAmount();
            sumDebitAmount = sumDebitAmount.add(debitAmount);

            BigDecimal repaymentRate = calculateRepaymentRate();
            sumRepaymentRate = sumRepaymentRate.add(repaymentRate);

            BigDecimal remainingDebt = calculateRemainingDebt();

            return new ScheduleItem(remainingDebt, debitAmount, repaymentRate, fixedRate);
        }
    }

    @Override
    public ScheduleItem overview() {
        if (debt == null) {
            throw new UnsupportedOperationException("for overview-result call calculate() first");
        }

        ScheduleItem overview = new ScheduleItem(debt, sumDebitAmount, sumRepaymentRate, sumDebitAmount.add(sumRepaymentRate));
        overview.setRateDate("Zinsbindungsende");

        return overview;
    }

    @Override
    public boolean earlyTerminated() {
        return earlyTerminated;
    }

    @Override
    BigDecimal calculateDebitAmount() {
        debitAmount = debt.multiply(debitRate).divide(MONTHS_COUNT, Calculator.InternalRounding)
                .setScale(Calculator.PRECISION, BigDecimal.ROUND_HALF_UP);
        return debitAmount;
    }

    @Override
    BigDecimal calculateRepaymentRate() {
        repaymentRate = fixedRate.subtract(debitAmount).setScale(Calculator.PRECISION, BigDecimal.ROUND_HALF_UP);
        return repaymentRate;
    }

    @Override
    BigDecimal calculateRemainingDebt() {
        debt = debt.subtract(repaymentRate).setScale(Calculator.PRECISION, BigDecimal.ROUND_HALF_UP);
        return debt;
    }

    private BigDecimal calculateFixedRate(BigDecimal debt, BigDecimal debitRate, String repaymentRate)  {
        BigDecimal firstInterestRate = debt.multiply(debitRate.divide(Calculator.Twelve, Calculator.InternalRounding));
        BigDecimal firstRepaymentRate = debt.multiply(new BigDecimal(repaymentRate)
                .divide(Calculator.OneHundred, Calculator.InternalRounding)
                .divide(Calculator.Twelve, Calculator.InternalRounding));

        return firstInterestRate.add(firstRepaymentRate).setScale(Calculator.PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    private boolean isEarlyTerminated() {
        earlyTerminated = debt.subtract(fixedRate).compareTo(BigDecimal.ZERO) == -1 ||
                debt.subtract(fixedRate).compareTo(BigDecimal.ZERO) == 0;

        return earlyTerminated;
    }
}