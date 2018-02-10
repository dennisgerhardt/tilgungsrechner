package com.mycompany.tilgungsrechner.exception;

public class ValidationException extends Exception {

    public enum ValidationErrorCode {
        INVALID_LOANAMOUNT,
        INVALID_DURATIONOFDEBIT,
        INVALID_FIXEDDEBITRATE,
        INVALID_REPAYMENTRATE,
        INVALID_PAYOUTDATE;
    }

    private final ValidationErrorCode validationErrorCode;

    public ValidationException(ValidationErrorCode validationErrorCode) {
        this.validationErrorCode = validationErrorCode;
    }

    public ValidationErrorCode getValidationErrorCode() {
        return validationErrorCode;
    }
}