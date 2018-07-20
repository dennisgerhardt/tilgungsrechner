package com.mycompany.tilgungsrechner.calculation;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.data.ScheduleItem;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Optional;

public class AnnuityLoanCalculatorTest {

    private CalculatorInput input;
    private Calculator calculator;

    @BeforeMethod
    public void beforeTest() {
        input = new CalculatorInput("100000.00", "10", "2.12", "2.0", "2018-02-28");
        calculator = CalculatorFactory.AnnuityLoan(input);
    }

    @Test
    public void testWholeAlgorithmFirstIteration() {
        final ScheduleItem item = calculator.calculate(Optional.of(true));

        Assert.assertEquals(item.getDebt(), "-99.833,34 €");
        Assert.assertEquals(item.getDebitAmount(), "176,67 €");
        Assert.assertEquals(item.getRepaymentRate(), "166,66 €");
        Assert.assertEquals(item.getFixedRate(), "343,33 €");
    }

    @Test
    public void testWholeAlgorithmSecondIteration() {
        calculator.calculate(Optional.of(true));
        final ScheduleItem second = calculator.calculate(Optional.of(true));

        Assert.assertEquals(second.getDebt(), "-99.666,38 €");
        Assert.assertEquals(second.getDebitAmount(), "176,37 €");
        Assert.assertEquals(second.getRepaymentRate(), "166,96 €");
        Assert.assertEquals(second.getFixedRate(), "343,33 €");
    }

    @Test
    public void testCalculateDebitAmount() {
        final BigDecimal debitAmount = calculator.calculateDebitAmount();
        Assert.assertEquals(debitAmount, new BigDecimal("176.67"));
    }

    @Test
    public void testCalculateRepaymentRate() {
        calculator.calculateDebitAmount();
        BigDecimal repaymentRate = calculator.calculateRepaymentRate();
        Assert.assertEquals(repaymentRate, new BigDecimal("166.66"));
    }

    @Test
    public void testCalculateRemainingDebt() {
        calculator.calculateDebitAmount();
        calculator.calculateRepaymentRate();
        final BigDecimal remainingDebt = calculator.calculateRemainingDebt();
        Assert.assertEquals(remainingDebt, new BigDecimal("99833.34"));
    }
}