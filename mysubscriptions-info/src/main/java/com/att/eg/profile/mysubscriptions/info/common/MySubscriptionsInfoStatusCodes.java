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
    INVALID_REQUEST_CONTEXT(1830),

    @MonitoredStatusCode("CONTENTWISE_CALL_REPORT")
    CONTENTWISE_CALL_REPORT(1800),

    @MonitoredStatusCode("LOCATION_CALL_REPORT")
    LOCATION_CALL_REPORT(1801),

    @MonitoredStatusCode("FAVORITE_CHANNEL_CALL_REPORT")
    FAVORITE_CHANNEL_CALL_REPORT(1802),

    @MonitoredStatusCode("IMAGE_METADATA_CALL_REPORT")
    IMAGE_METADATA_CALL_REPORT(1803),

    @MonitoredStatusCode("FAIL_TO_LOAD_ZIP_CODE")
    FAIL_TO_LOAD_ZIP_CODE(1804),

    @MonitoredStatusCode("FAVORITE_CHANNEL_IS_EMPTY")
    FAVORITE_CHANNEL_IS_EMPTY(1805),

    @MonitoredStatusCode("NO_FIS_PROPERTIES_FOUND")
    NO_FIS_PROPERTIES_FOUND(1806),

    @MonitoredStatusCode("INPUT_VALIDATION_FAILURE")
    INPUT_VALIDATION_FAILURE(1807),

    @MonitoredStatusCode("ASYNCHRONOUS_PROCESS_ERROR")
    ASYNCHRONOUS_PROCESS_ERROR(1808),

    @MonitoredStatusCode("LOCATION_CALL_FAILURE")
    LOCATION_CALL_FAILURE(1809),

    @MonitoredStatusCode("FAVORITE_CHANNEL_CALL_FAILURE")
    FAVORITE_CHANNEL_CALL_FAILURE(1810),

    @MonitoredStatusCode("IMAGE_METADATA_CALL_FAILURE")
    IMAGE_METADATA_CALL_FAILURE(1811),

    @MonitoredStatusCode("INVALID_LOCATION_RESPONSE")
    INVALID_LOCATION_RESPONSE(1812),

    @MonitoredStatusCode("INVALID_FAVORITE_CHANNEL_RESPONSE")
    INVALID_FAVORITE_CHANNEL_RESPONSE(1813),

    @MonitoredStatusCode("INVALID_IMAGE_METADATA_RESPONSE")
    INVALID_IMAGE_METADATA_RESPONSE(1814),

    @MonitoredStatusCode("LOCATION_RESPONSE_PARSING_EXCEPTION")
    LOCATION_RESPONSE_PARSING_EXCEPTION(1815),

    @MonitoredStatusCode("FAVORITE_CHANNEL_RESPONSE_PARSING_EXCEPTION")
    FAVORITE_CHANNEL_RESPONSE_PARSING_EXCEPTION(1816),

    @MonitoredStatusCode("IMAGE_METADATA_RESPONSE_PARSING_EXCEPTION")
    IMAGE_METADATA_RESPONSE_PARSING_EXCEPTION(1817),

    @MonitoredStatusCode("SDK_RETURN_NON_CHANNEL_OBJECT")
    SDK_RETURN_NON_CHANNEL_OBJECT(1818),

    @MonitoredStatusCode("CONTENTWISE_CALL_FAILURE")
    CONTENTWISE_CALL_FAILURE(1819),

    @MonitoredStatusCode("INVALID_CONTENTWISE_RESPONSE")
    INVALID_CONTENTWISE_RESPONSE(1820),

    @MonitoredStatusCode("CONTENTWISE_RESPONSE_PARSING_EXCEPTION")
    CONTENTWISE_RESPONSE_PARSING_EXCEPTION(1821),

    @MonitoredStatusCode("CHANNEL_FILTER_FAILURE")
    CHANNEL_FILTER_FAILURE(1822),

    @MonitoredStatusCode("CHANNEL_PAGINATION_FAILURE")
    CHANNEL_PAGINATION_FAILURE(1823),

    @MonitoredStatusCode("CHANNEL_AFFILIATE_LOADING_FAILURE")
    CHANNEL_AFFILIATE_LOADING_FAILURE(1824),

    @MonitoredStatusCode("NO_FAVORITE_CHANNEL_FOUND")
    NO_FAVORITE_CHANNEL_FOUND(1825),

    @MonitoredStatusCode("UNKNOWN_EXCEPTION_OCCURED")
    UNKNOWN_EXCEPTION_OCCURED(1899);

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
