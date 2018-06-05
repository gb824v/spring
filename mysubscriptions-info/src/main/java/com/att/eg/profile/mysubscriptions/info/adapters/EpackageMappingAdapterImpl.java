package com.att.eg.profile.mysubscriptions.info.adapters;

import com.att.eg.monitoring.annotations.statuscodes.CommonStatusCodes;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.common.Constants;
import com.att.eg.profile.mysubscriptions.info.common.FailureException;
import com.att.eg.profile.mysubscriptions.info.common.MySubscriptionsInfoErrorType;
import com.att.eg.profile.mysubscriptions.info.common.MySubscriptionsInfoStatusCodes;
import com.att.eg.profile.mysubscriptions.info.common.dme.DME;
import com.att.eg.profile.mysubscriptions.info.model.EpackageMappingResponse;
import com.att.eg.profile.mysubscriptions.info.util.JsonUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.ws.rs.core.Response;


@Service
public class EpackageMappingAdapterImpl implements EpackageMappingAdapter {
    private final YawlLogger yawl = new YawlLogger(EpackageMappingAdapterImpl.class);
    private DME dme;
    private Integer timeout;
    private String target;
    private String path;
    private String version;
    private String routeOffer;
    private String envContext;
    private String dmePartner;
    private boolean isUseRouteOfferRequestHeaderFlag;

    private EpackageMappingAdapterImpl(@Value("${epackagemapping.dme.target}") String target,//NOSONAR
                                       @Value("${epackagemapping.dme.path}") String path,
                                       @Value("${epackagemapping.dme.timeout}") Integer timeout,
                                       @Value("${epackagemapping.dme.version}") String version,
                                       @Value("${epackagemapping.dme.env.context}") String envContext,
                                       @Value("${epackagemapping.dme.routeOffer}") String routeOffer,
                                       @Value("${epackagemapping.dme.partner}") String dmePartner,
                                       @Value("${epackagemapping.dme.use.route.offer.request.header}") Boolean isUseRouteOfferRequestHeaderFlag,
                                       DME dme) {
        this.target = target;
        this.path = path;
        this.timeout = timeout;
        this.version = version;
        this.envContext = envContext;
        this.routeOffer = routeOffer;
        this.dmePartner = dmePartner;
        this.isUseRouteOfferRequestHeaderFlag = isUseRouteOfferRequestHeaderFlag;
        this.dme = dme;
    }

    @Override
    public EpackageMappingResponse getPackage(String packageCode) {
        EpackageMappingResponse epackageMappingResponse = null;
        try {
            String clientURI = Constants.HTTPS_PREFIX
                    + target
                    + path;
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put(Constants.PACKAGE_CODE, packageCode);
            DME.Params dmeParams = getDmeParams(
                    clientURI,
                    queryParams,
                    null,
                    Constants.GET,
                    version,
                    envContext,
                    routeOffer,
                    timeout,
                    null,
                    dmePartner,
                    isUseRouteOfferRequestHeaderFlag);
            DME.Response response = callService(dmeParams);
            if (response.getBody().startsWith("[") && response.getBody().endsWith("]")) {
                epackageMappingResponse = parseResponse(response.getBody().substring(1, response.getBody().length() - 1));
            }

        } catch (Exception e) {
            yawl.debug(CommonStatusCodes.INVALID_INPUT_PARAMTERS,
                    new MetaBuilder().fromException(e, this.getClass().getSimpleName()).create());
        }
        return epackageMappingResponse;
    }


    private DME.Response callService(DME.Params params) {

        StopWatch stopWatch = new StopWatch("epackageMappingResponse Service StopWatch");
        DME.Response response = null;
        stopWatch.start();

        try {
            response = dme.callService(params);
            stopWatch.stop();
        } catch (Exception e) {
            stopWatch.stop();
            yawl.error(MySubscriptionsInfoStatusCodes.PROGRAM_DETAILS_FETCH_ERROR, new MetaBuilder()
                    .setReason(e.getMessage())
                    .setNote("")
                    .fromException(e, this.getClass().getName())
                    .setKeyAndValue(com.att.eg.profile.mysubscriptions.info.common.Constants.DME_PARAMS, params.toString())
                    .create());
            throw new FailureException(MySubscriptionsInfoErrorType.MULTI_PROGRAMS_FETCH_FAILURE, "DME Exception");
        }
        checkDmeResponse(response, params);

        return response;
    }

    private void checkDmeResponse(DME.Response response, DME.Params params) {
        if (response.getCode() == Response.Status.OK.getStatusCode()) {
            return;
        }
        //else
        yawl.error(MySubscriptionsInfoStatusCodes.INVALID_PROGRAM_OFFERING_RESPONSE, new MetaBuilder()
                .setNote("program response failure invalid status")
                .setKeyAndValue(com.att.eg.profile.mysubscriptions.info.common.Constants.DME_RESPONSE, Objects.toString(response))
                .setKeyAndValue(com.att.eg.profile.mysubscriptions.info.common.Constants.DME_PARAMS, params.toString())
                .create());

        if (response.getCode() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw new FailureException(MySubscriptionsInfoErrorType.CANONICAL_ITEMS_NOT_FOUND, "canonical items not found");
        } else {
            throw new FailureException(MySubscriptionsInfoErrorType.PROGRAM_FETCH_FAILURE, "response code: " + response.getCode());
        }
    }

    public static DME.Params getDmeParams(String clientUri, //NOSONAR
                                          Map<String, String> queryParams,
                                          Map<String, String> headers,
                                          String method,
                                          String version,
                                          String envContext,
                                          String routeOffer,
                                          Integer timeout,
                                          String payload,
                                          String dmePartner,
                                          boolean isUseRouteOfferRequestHeaderFlag) {

        return new DME.Params(
                method,
                clientUri,
                timeout,
                null,
                queryParams,
                headers,
                payload,
                version,
                envContext,
                routeOffer,
                dmePartner,
                isUseRouteOfferRequestHeaderFlag);
    }

    private EpackageMappingResponse parseResponse(String body) {
        EpackageMappingResponse epackageMappingResponse;
        JsonUtils jsonUtils = new JsonUtils();
        try {
            epackageMappingResponse = jsonUtils.getObject(body, EpackageMappingResponse.class);
        } catch (Exception e) {
            yawl.error(CommonStatusCodes.PARSING_FAILURE, new MetaBuilder()
                    .setReason(e.getMessage())
                    .setNote("Could not serialize e-package mapping")
                    .setKeyAndValue(com.att.eg.profile.mysubscriptions.info.common.Constants.BODY, body)
                    .fromException(e, this.getClass().getName())
                    .create());
            throw new FailureException(MySubscriptionsInfoErrorType.PROGRAM_PARSE_FAILURE, "Could not serialize Program Offerings");
        }
        return epackageMappingResponse;
    }
}