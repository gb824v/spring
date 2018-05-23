package com.att.eg.profile.mysubscriptions.info.common;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.FORBIDDEN;
import static javax.ws.rs.core.Response.Status.Family;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

/**
 * Created by mguttman on 03/10/2017.
 */
public enum MySubscriptionsInfoErrorType {
    SERIES_ITEMS_NOT_FOUND (452, "Not Found Series Item", "000"),
    NO_SERIES_ITEM_MATCH_RULES_NOT_FIRST_RUN_EXIST (FORBIDDEN, "007"),
    CANONICAL_ITEMS_NOT_FOUND (452, "Not Found Canonical Item", "000"),
    RESOURCE_DETAILS_NOT_FOUND (452, "Not Found Resource Item", "000"),
    RESOURCE_TIME_NOT_MATCH (452, "Not Found Resource In Future", "000"),
    RESOURCE_LOCATION_NOT_MATCH (453, "Location Not Match", "000"),
    NOT_RECORDABLE(451, "Not Recordable", "000"),
    NOT_SUBSCRIBED(FORBIDDEN, "010"),
    NO_ITEM_MATCH_RULES(452, "Not Match", "000"),
    //server exceptions
    UNKNOWN_FAILURE (INTERNAL_SERVER_ERROR, "001"),
    SERIES_FETCH_FAILURE (INTERNAL_SERVER_ERROR, "002"),
    SERIES_PARSE_FAILURE (INTERNAL_SERVER_ERROR, "003"),
    SERIES_ITEM_DETAILS_NOT_FOUND (INTERNAL_SERVER_ERROR, "004"),
    PROGRAM_FETCH_FAILURE (INTERNAL_SERVER_ERROR, "005"),
    PROGRAM_PARSE_FAILURE (INTERNAL_SERVER_ERROR, "006"),
    PROGRAM_DETAILS_FETCH_FAILURE (INTERNAL_SERVER_ERROR, "007"),
    CANONICAL_ITEM_DETAILS_NOT_FOUND (INTERNAL_SERVER_ERROR, "008"),
    PROGRAM_DETAILS_PARSE_FAILURE (INTERNAL_SERVER_ERROR, "009"),
    RIGHT_LOCATION_FETCH_FAILURE (INTERNAL_SERVER_ERROR, "010"),
    RIGHT_LOCATION_PARSE_FAILURE (INTERNAL_SERVER_ERROR, "011"),
    MULTI_PROGRAMS_FETCH_FAILURE (INTERNAL_SERVER_ERROR, "012"),
    MULTI_PROGRAMS_PARSE_FAILURE (INTERNAL_SERVER_ERROR, "013"),
    MULTI_PROGRAMS_NOT_FOUND (INTERNAL_SERVER_ERROR, "014");


    private Response.StatusType statusType;
    private String subErrorCode;

    MySubscriptionsInfoErrorType(Response.StatusType statusType, String errorCode) {
        this.statusType = statusType;
        subErrorCode = errorCode;
    }



    private class OffersHttpStatusType implements Response.StatusType {
        private final int code;
        private final String reason;
        private final Family family;

        private OffersHttpStatusType(int statusCode, String reasonPhrase) {
            this.code = statusCode;
            this.reason = reasonPhrase;
            this.family = Family.familyOf(statusCode);
        }

        @Override
        public int getStatusCode() {
            return code;
        }

        @Override
        public Family getFamily() {
            return family;
        }

        @Override
        public String getReasonPhrase() {
            return reason;
        }
    }

    MySubscriptionsInfoErrorType(int httpCode, String reasonPhrase, String subErrorCode) {
        this.statusType = new OffersHttpStatusType(httpCode, reasonPhrase);
        this.subErrorCode = subErrorCode;
    }

    public String getErrorCode() {
        return subErrorCode;
    }

    public Response.StatusType getStatusType() {
        return statusType;
    }
}
