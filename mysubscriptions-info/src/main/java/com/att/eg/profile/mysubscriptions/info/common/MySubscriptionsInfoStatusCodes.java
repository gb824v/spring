package com.att.eg.profile.mysubscriptions.info.common;

import com.att.eg.monitoring.annotations.statuscodes.IntegerValued;
import com.att.eg.monitoring.annotations.statuscodes.MonitoredStatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mguttman on 24/08/2017.
 */
public enum MySubscriptionsInfoStatusCodes implements IntegerValued {
    @MonitoredStatusCode("Missing / Invalid required parameter")
    INVALID_REQUIRED_PARAMETER(1800),

    @MonitoredStatusCode("Series Offering Fetch Error")
    SERIES_OFFERINGS_FETCH_ERROR(1801),

    @MonitoredStatusCode("Invalid Series Offering Response Object")
    INVALID_SERIES_OFFERING_RESPONSE(1802),

    @MonitoredStatusCode("Reporting Metadata Series Call")
    METADATA_SERIES_CALL_REPORT(1803),

    @MonitoredStatusCode("Program Offering Fetch Error")
    PROGRAM_OFFERINGS_FETCH_ERROR(1804),

    @MonitoredStatusCode("Invalid Program Offering Response Object")
    INVALID_PROGRAM_OFFERING_RESPONSE(1805),

    @MonitoredStatusCode("Reporting Metadata Program Call")
    METADATA_PROGRAM_CALL_REPORT(1806),

    @MonitoredStatusCode("Channel Fetch Error")
    CHANNEL_FETCH_ERROR(1807),

    @MonitoredStatusCode("Invalid Channel Response Object")
    INVALID_CHANNEL_RESPONSE(1808),

    @MonitoredStatusCode("Reporting Metadata Channel Call")
    METADATA_CHANNEL_CALL_REPORT(1809),

    @MonitoredStatusCode("Program Details Fetch Error")
    PROGRAM_DETAILS_FETCH_ERROR(1810),

    @MonitoredStatusCode("Invalid Program Details Response Object")
    INVALID_PROGRAM_DETAILS_RESPONSE(1811),

    @MonitoredStatusCode("Reporting Metadata Program Details Call")
    METADATA_PROGRAM_DETAILS_CALL_REPORT(1812),

    @MonitoredStatusCode("DMA Fetch Error")
    AREA_FETCH_ERROR(1813),

    @MonitoredStatusCode("Invalid DMA Response Object")
    INVALID_AREA_RESPONSE(1814),

    @MonitoredStatusCode("Reporting Metadata DMA Call")
    RIGHT_LOCATION_AREA_CALL_REPORT(1815),

    @MonitoredStatusCode("Detailed meta data not found")
    DETAILED_METADATA_NOT_FOUND(1816),

    @MonitoredStatusCode("Multi Programs Fetch Error")
    MULTI_PROGRAMS_DETAILS_FETCH_ERROR(1817),

    @MonitoredStatusCode("Multi Programs Invalid Response Object")
    MULTI_PROGRAMS_INVALID_RESPONSE(1818),

    @MonitoredStatusCode("Multi Programs Call Report")
    MULTI_PROGRAMS_CALL_REPORT(1819),

    @MonitoredStatusCode("Multi Programs Not Found")
    MULTI_PROGRAMS_NOT_FOUND(1820),

    @MonitoredStatusCode("Multi Programs Partially Not Found")
    MULTI_PROGRAMS_PARTIALLY_NOT_FOUND(1821),

    @MonitoredStatusCode("Get Multiple Offers Failure")
    GET_MULTIPLE_OFFERS_FAILURE(1822),

    @MonitoredStatusCode("Get Multiple Offers Unexpected Failure")
    GET_MULTIPLE_OFFERS_UNEXPECTED_FAILURE(1823),

    @MonitoredStatusCode("Get Multiple Offers Succeeded")
    GET_MULTIPLE_OFFERS_SUCCEEDED(1824),

    @MonitoredStatusCode("Get Offer Failure")
    GET_OFFER_FAILURE(1825),

    @MonitoredStatusCode("Get Offer Unexpected Failure")
    GET_OFFER_UNEXPECTED_FAILURE(1826),

    @MonitoredStatusCode("Get Offer Succeeded")
    GET_OFFER_SUCCEEDED(1827),

    @MonitoredStatusCode("No Items Found Series Offering Response")
    NO_ITEMS_FOUND_SERIES_OFFERING_RESPONSE(1828),
    
    @MonitoredStatusCode("Missing client context - calling area service for dma information")
    CLIENT_CONTEXT_MISSING(1829),
    
    @MonitoredStatusCode("Missing client context and zipcode - request is invalid")
    INVALID_REQUEST_CONTEXT(1830);

    private static final Map<Integer, MySubscriptionsInfoStatusCodes> lookup = new HashMap<>();

    static {
        for (MySubscriptionsInfoStatusCodes lsc : MySubscriptionsInfoStatusCodes.values()) {
            lookup.put(lsc.getValue(), lsc);
        }
    }

    private final int value;

    MySubscriptionsInfoStatusCodes(final int value) {
        this.value = value;
    }

    public static MySubscriptionsInfoStatusCodes get(int value) {
        return lookup.get(value);
    }

    @Override
    public int getValue() {
        return value;
    }

}
