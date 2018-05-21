package com.att.eg.profile.mysubscriptions.info.util;

import com.att.eg.monitoring.annotations.statuscodes.IntegerValued;
import com.att.eg.monitoring.annotations.statuscodes.MonitoredStatusCode;

public enum RBSYawlCodes implements IntegerValued {

    @MonitoredStatusCode("Series booking reqest")
    BOOKING_SERIES_REQ(104),

    @MonitoredStatusCode("returning 403")
    UNAUTHORIZED(1805),

    @MonitoredStatusCode("Offers service call failed")
    OFFERS_CALL_FAILURE(1806),

    @MonitoredStatusCode("an exception occurred while calling making DME call")
    EXCEPTION_DME_CALL(1820);

    private final int value;

    RBSYawlCodes(final int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}