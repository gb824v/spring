package com.att.eg.profile.mysubscriptions.info.model;

import com.att.eg.monitoring.annotations.statuscodes.IntegerValued;
import com.att.eg.monitoring.annotations.statuscodes.MonitoredStatusCode;

/**
 * The status of a request to be returned to the client.
 * Taken from the example documentation for MonitoredStatusCode.
 * @author cdebergh
 *
 */
public enum Status implements IntegerValued {
    @MonitoredStatusCode("Success")
    OK(0),
    @MonitoredStatusCode("Something bad happened")
    ERROR_GENERIC(1),
    @MonitoredStatusCode("Token expired")
    ERROR_TOKEN_EXPIRED(2),
    @MonitoredStatusCode("Session expired")
    ERROR_SESSION_EXPIRED(3);
    
    private int value;

    private Status(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
