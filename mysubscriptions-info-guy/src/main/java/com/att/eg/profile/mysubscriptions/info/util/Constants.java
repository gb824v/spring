package com.att.eg.profile.mysubscriptions.info.util;

/**
 * This class store constants values for the application.
 *
 */
public final class Constants {

   //Ids
   public static final String RESOURCE_ID = "resourceId";
   public static final String FIELD = "field";
   public static final String VALUE = "value";
   public static final String BOOKING_ID = "bookingId";
   public static final String ACCOUNT_TOKEN = "accountToken";
   public static final String USER_PROFILE_ID = "UserProfileId";
   public static final String EVENT_ID = "eventId";
   public static final String CANONICAL_ID = "canonicalId";
   public static final String HOUSEHOLDID_STRING = "{householdId}";
   public static final String SERIES_ID = "seriesId";
   public static final String FILTER_STATE = "filter:state";
   public static final String RECORDING_ID = "recordingId";

   public static final String BOOKING_REQUEST = "bookingRequest";
   public static final String BOOKING_SERIES_RESPONSE = "BookingSeriesResponse";
   public static final String BOOKING_SINGLE_RESPONSE = "BookingSingleResponse";

   public static final String PPS_RECURRENCE = "NONE";
   public static final String CONTENT_TYPE = "Content-Type";

   public static final String REASON = "reson";
   public static final String ALREADY_BOOKED = "Series already booked";

   public static final String BODY = "body";
   public static final String ERROR_CODE="Error Code";
   public static final String STATUS_CODE_UNKOWN="HTTP Status Code unresolvable";
   public static final String URL = "url";

   //Failure responses
   public static final String NOT_OK = "received non 200 status code";
   public static final String STATUS_CODE = "status code";
   public static final String EXCEPTION = "exception occurred";
   public static final String EMPTY_RESP = "service returned empty response";
   public static final String BAD_REQUEST_ERROR_CODE = "000";
   //PPS response
   public static final String PPS_MESSAGE = "PPS Message";
   public static final String PPS_FAILURE = "PPS Failure";
   public static final String PPS_FULL_STATUS_CODE = "full status code";
   public static final String RESOURCE_ALREADY_BOOKED = "Resource Already booked";
   public static final String PPS_403="PPS returned 403";
   public static final String PPS_403_ERROR_CODE = "403";
   public static final String PPS_001_ERROR_CODE = "001";
   public static final String PPS_005_ERROR_CODE = "005";
   public static final String PPS_004_SUB_ERROR_CODE = "004";
   public static final String PPS_004_ERROR_CODE = "004";
   public static final String PPS_007_RECURRENCE_ALREADY_EXIST = "007";
   public static final String PPS_006_RECURRENCE_ALREADY_EXIST = "006";

   public static final String RESOURCE_ALREADY_RECORDED = "006";

   public static final String PPS_ALREADY_EXISTS ="409.200";
   public static final String OFFERS_NOT_FOUND = "001";
   public static final String X_AEG_PARTNERP_ROFILEID = "X-AEG-PartnerProfileID";
   public static final String X_AEG_ACCOUNT_TOKEN = "X-AEG-Account-Token";
   public static final String X_AEG_ROUTE_OFFER_HEADER = "x-aeg-route-offer";
   public static final String X_AEG_ZIP_CODE="X-AEG-ZIP-Code";
   //Service responses
   public static final String PPS_RESP = "PPS_resp";
   public static final String BOOKINGSTORE_RESP = "Bookingstore_response";
   public static final String RECORDING_RESP = "Recording_response";
   public static final String RECORDING_DATA = "Recording data";
   public static final String OFFERS_RESP = "offers_resp";
   public static final String NULL_INPUT = "null input";

   public static final String HTTPS_PREFIX = "https://";

   public static final String OFFERS_RESP_PREFIX = "Offers:";
   public static final String ACCOUNT_RESP_PREFIX = "HHS:";
   public static final String BS_RESP_PREFIX = "BS:";
   public static final String RS_RESP_PREFIX = "RS";
   public static final String FLOW_CONTEXT_HEADER = "FLOW_CONTEXT";
   public static final String X_ATT_TRANSACTION_ID = "X-ATT-TransactionId";
   public static final String TRANSACTION_ID = "x-att-transactionid";
   public static final String HEADERS = "headers";

   public static final int HTTP_500=500;
   
   public static final String RESP_CODE="RESP_CODE";
   public static final String RESP_ERROR="RESP_ERROR";
   public static final String SING_BKG_RESP_TIME="Single_Booking_response_time";
   public static final String SER_BKG_RESP_TIME="Series_Booking_response_time";

   public static final String REC_KEEP_ALL="ALL";

   public static final String CLIENT_CONTEXT = "clientContext";
   
   public static final String COMPOSITE_ID_SEPARATOR = "~";
   public static final String COMPOSITE_ID  = "compositeId";
   public static final String AUTO_DELETE_DAYS = "/autoDeleteDays";
   public static final String RCORDINGS_TO_KEEP = "/recurrence/recordingsToKeep";
   public static final String LINK_EPISODE_TYPE = "/recurrence/linkEpisodeType";
   
   public static final String DMA_ID_PARAM_NAME = "dmaId";
   public static final String ZIP_CODE = "zipCode";
   public static final String CONTEXT_VALUE_DELIMITER = ":";
   public static final String BILLING_DMA_ID = "billingDmaID";
   public static final String CONTEXT_FIELD_DELIMITER = ",";
   public static final String MARKET_ID = "marketId";
   public static final String ZIP_CODE_REQ_PARAM = "zipcode";
   public static final String No_DmaID = "No DmaId";


   private Constants(){
      throw new IllegalAccessError("Utility class");
   }


}
