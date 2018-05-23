package com.att.eg.profile.mysubscriptions.info.common;

/**
 * Created by mguttman on 03/09/2017.
 */
public class FailureException extends RuntimeException {
    private final MySubscriptionsInfoErrorType errorType;

    public FailureException(MySubscriptionsInfoErrorType errorType, String failureReason) {
        super(failureReason);
        this.errorType = errorType;
    }

    public MySubscriptionsInfoErrorType getErrorType() {
        return errorType;
    }
}
