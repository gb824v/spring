package com.att.eg.profile.mysubscriptions.info.util;


import com.att.eg.monitoring.annotations.statuscodes.IntegerValued;
import com.att.eg.monitoring.annotations.statuscodes.MonitoredStatusCode;

public enum YawlCodes implements IntegerValued {

    @MonitoredStatusCode("Failed to get channels from catalog-discover channels service")
    CHANNELS_GET_ERROR(1805),
    @MonitoredStatusCode("ASYNCHRONOUS_PROCESS_ERROR")
    ASYNCHRONOUS_PROCESS_ERROR(1806),
    @MonitoredStatusCode("IMAGE_METADATA_CALL_FAILURE")
    IMAGE_METADATA_CALL_FAILURE(1807),
    @MonitoredStatusCode("GET_SUBSCRIPTION_COMPARISON")
    GET_SUBSCRIPTION_COMPARISON(1808);

    private final int value;

    YawlCodes(final int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
