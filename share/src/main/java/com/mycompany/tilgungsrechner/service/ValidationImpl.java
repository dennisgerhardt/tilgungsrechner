package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.exception.ValidationException;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ValidationImpl implements IValidation {

    private static final Pattern VALID_NUMBER = Pattern.compile("^[0-9]+([.]\\d{1,2})?$");
    private static final Pattern VALID_PERCENTAGE = Pattern.compile("^\\d{1,2}([.]\\d{1,2})?$");
    private static final Pattern VALID_DURATION = Pattern.compile("^\\d{1,2}$");

    final Predicate<String> IsNotNullOrEmpty;
    final Predicate<String> IsValidNumber, IsValidPercentage, IsValidDurationStr;

    private final Predicate<Double> IsValidLoanAmount;
    private final Predicate<Integer> IsValidDuration;

    ValidationImpl() {
        IsNotNullOrEmpty = value -> value != null && !value.isEmpty();
        IsValidNumber = value -> VALID_NUMBER.matcher(value).matches();
        IsValidPercentage = value -> VALID_PERCENTAGE.matcher(value).matches();
        IsValidDurationStr = value -> VALID_DURATION.matcher(value).matches();
        IsValidLoanAmount = value -> value >= 10000 && value <= 1000000;
        IsValidDuration = value -> value >= 5 && value <= 35;
    }

    @Override
    public boolean validateLoanAmount(String value) {
        final boolean test = IsNotNullOrEmpty.and(IsValidNumber).test(value);

        if (!test) {
            return false;
        }

        final double doubleValue = Double.valueOf(value);

        return IsValidLoanAmount.test(doubleValue);
    }

    @Override
    public boolean validatePercentage(String value) {
        return IsNotNullOrEmpty.and(IsValidPercentage).test(value);
    }

    @Override
    public boolean validateDuration(String value) {
        final boolean test = IsNotNullOrEmpty.and(IsValidDurationStr).test(value);

        if (!test) {
            return false;
        }

        final int intValue = Integer.valueOf(value);

        return IsValidDuration.test(intValue);
    }

    @Override
    public void validate(CalculatorInput input) throws ValidationException {
        final boolean validDuration = validateDuration(input.getDurationOfDebit());
        if (!validDuration) {
            throw new ValidationException(ValidationException.ValidationErrorCode.INVALID_DURATIONOFDEBIT);
        }

        final boolean validLoanAmount = validateLoanAmount(input.getLoanAmount());
        if (!validLoanAmount) {
            throw new ValidationException(ValidationException.ValidationErrorCode.INVALID_LOANAMOUNT);
        }

        final boolean validFixedDebitRate = validatePercentage(input.getFixedDebitRate());
        if (!validFixedDebitRate) {
            throw new ValidationException(ValidationException.ValidationErrorCode.INVALID_FIXEDDEBITRATE);
        }

        final boolean validRepaymentRate = validatePercentage(input.getRepaymentRate());
        if (!validRepaymentRate) {
            throw new ValidationException(ValidationException.ValidationErrorCode.INVALID_REPAYMENTRATE);
        }
    }
}