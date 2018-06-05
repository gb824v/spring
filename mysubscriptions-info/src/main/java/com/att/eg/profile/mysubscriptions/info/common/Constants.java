package com.att.eg.profile.mysubscriptions.info.common;

/**
 * Created by mguttman on 21/08/2017.
 */
public class Constants {
    public static final String TRANSACTION_ID = "x-att-transactionid";
    public static final String X_AEG_ENTERTAINMENT_PROFILE = "x-aeg-entertainment-profile-id";
    public static final String X_AEG_ACCOUNT_TOKEN = "x-aeg-account-token";
    public static final String X_AEG_PROFILE_ID = "x-aeg-profile-id";
    public static final String HTTPS_PREFIX = "https://";
    public static final String PACKAGE_CODE = "packageCode";
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String OK = "200";
    public static final String QUEUE_SIZE = "Queue_Size";
    //Failure responses
    public static final String NOT_OK = "received non 200 status code";
    public static final String EXCEPTION = "exception occurred";
    public static final String STATUS = "status:";
    public static final String ATS_TOKEN = "DA:1524842238026:4AoxsvuYb4MiPyS2RePRfdI6yKM";
    public static final String BASE_PRODUCT_PREFIX = "BASE";

    public static final String SERIES_ID = "SeriesID";
    public static final String CONTENT_ID = "ContentID";
    public static final String CHANNEL_ID = "ChannelID";

    public static final String CANONICAL_ID = "CanonicalID";
    public static final String RESOURCE_ID = "ResourceID";
    public static final String CC_ID = "ccId";

    public static final String CONTENT = "Content";
    public static final String REQUEST_TYPE = "RequestType";
    public static final String REQUEST_INFO = "RequestInfo";

    public static final String IS_RECORDABLE = "IsRecordable";

    public static final String PARTNER_PROFILE_ID = "PartnerProfileId";
    public static final String ZIP_CODE = "ZipCode";
    public static final String DME_PARAMS = "DME_Params";
    public static final String DME_RESPONSE = "DME_Response";
    public static final String RESPONSE_TIME = "ResponseTime_MS";
    public static final String BODY = "Body";
    public static final String RESOURCES_STRING = "resources";
    public static final String START_TIME_STRING = "StartTime";

    public static final String ERROR_TYPE_STRING = "ErrorType";
    public static final String ERROR_CODE_STRING = "ErrorCode";
    public static final String SUB_ERROR_CODE_STRING = "SubErrorCode";

    public static final String NATIONAL_MARKET_ID = "0";
    public static final String TIME_PAGINATION_NUMBER = "TimePaginationNumber";
    public static final String IS_DE_PRIORITIZED_CHANNEL = "IsDePrioritizedChannel";

    // DMA parameters
    public static final String DMA_ID_PARAM_NAME = "dmaId";
    public static final String CLIENT_CONTEXT_PARAM_NAME = "clientContext";
    public static final String BILLING_DMA_ID = "billingDmaID";
    public static final String DMA_ID_CONTEXT_KEY = "dmaID";
    public static final String ZIP_CODE_CONTEXT_KEY = "zipCode";
    public static final String CONTEXT_FIELD_DELIMITER = ",";
    public static final String CONTEXT_VALUE_DELIMITER = ":";

    // discovery parametes name
    public static final String MARKET_ID_PARAM_NAME = "marketId";
    public static final String ITEM_COUNT_PARAM_NAME = "itemCount";
    public static final String START_TIME_PARAM_NAME = "startTime";
    public static final String END_TIME_PARAM_NAME = "endTime";

    public static final String DATE_FORMAT_STR = "yyyy.MM.dd-HH:mm:ss";
    public static final String VIDEO_PROGRAM_ITEM_TYPE = "VIDEO_PROGRAM";

    public static final String SERIES_OFFERINGS_REQUEST_PARAMS = "SeriesOfferingsRequestParams";
    public static final String PROFILE_ID = "ProfileId";
    public static final String SECURE_TOKEN = "Secure Token";
    public static final String SUBSCRIPTION_ID = "Subscription id";
    public static final String ROUTE_OFFER = "Route Offer";
    public static final String FISPROPERTIES = "fisProperties";

    private Constants() {
        //empty constructor to fix SonarQube error
    }
}
