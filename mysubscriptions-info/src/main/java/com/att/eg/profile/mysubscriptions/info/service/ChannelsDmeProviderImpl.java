package com.att.eg.profile.mysubscriptions.info.service;

import com.att.ajsc.common.exception.BadRequestException;
import com.att.eg.monitoring.annotations.statuscodes.CommonStatusCodes;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.common.dme.DME;
import com.att.eg.profile.mysubscriptions.info.common.Constants;
import com.att.eg.profile.mysubscriptions.info.common.FailureException;
import com.att.eg.profile.mysubscriptions.info.common.MySubscriptionsInfoErrorType;
import com.att.eg.profile.mysubscriptions.info.common.MySubscriptionsInfoStatusCodes;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import static org.slf4j.LoggerFactory.*;

@Service
public class ChannelsDmeProviderImpl implements ChannelsDmeProvider {
    private final YawlLogger yawl = new YawlLogger(ChannelsDmeProviderImpl.class);
    private final Logger fLogger = getLogger(this.getClass().getPackage().getName());
    private DME dme;
    private Integer timeout;
    private String target;
    private String path;
    private String version;
    private String routeOffer;
    private String envContext;
    private String dmePartner;
    private boolean isUseRouteOfferRequestHeaderFlag;

    private ChannelsDmeProviderImpl(@Value("${channels.target}") String target,
                                    @Value("${channels.path}") String path,
                                    @Value("${channels.timeout}") Integer timeout,
                                    @Value("${channels.version}") String version,
                                    @Value("${channels.env.context}") String envContext,
                                    @Value("${channels.routeOffer}") String routeOffer,
                                    @Value("${channels.partner}") String dmePartner,
                                    @Value("${channels.use.route.offer.request.header}") Boolean isUseRouteOfferRequestHeaderFlag,
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
    public DME.Response getChannels(HttpHeaders headers) {
        DME.Response response = null;
        MultivaluedMap<String, String> reqHeaders = headers.getRequestHeaders();
        String token = reqHeaders.getFirst("x-aeg-profile-id");
        try {

            String clientURI = "http://"
                    + target
                    + path;
            Map<String, String> headersMap = new HashMap<String, String>();
            headersMap.put("x-aeg-profile-id", token);
            headersMap.put("Content-Type", "application/json");
            String payload = "{\"entitlements\": [],\"filters\": {\"subscription\": \"ALL\",\"channelType\": \"LINEAR\"}}";
            DME.Params dmeParams = getDmeParams(
                    clientURI,
                    null,
                    headersMap,
                    version,
                    envContext,
                    routeOffer,
                    timeout,
                    payload,
                    dmePartner,
                    isUseRouteOfferRequestHeaderFlag);


            response = callService(dmeParams);
            int currentStatus = response.getCode();
        } catch (BadRequestException e) {
            yawl.debug(CommonStatusCodes.INVALID_INPUT_PARAMTERS,
                    new MetaBuilder().fromException(e, this.getClass().getSimpleName()).create());


        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    private DME.Response callService(DME.Params params) {

        StopWatch stopWatch = new StopWatch("Discovery MySubscriptionsInfo Service StopWatch");
        DME.Response response = null;
        stopWatch.start();

        try {
            response = dme.callService(params);
            stopWatch.stop();
        } catch (Exception e) {
            stopWatch.stop();
            yawl.error(MySubscriptionsInfoStatusCodes.MULTI_PROGRAMS_DETAILS_FETCH_ERROR, new MetaBuilder()
                    .setReason(e.getMessage())
                    .setNote("")
                    .fromException(e, this.getClass().getName())
                    .setKeyAndValue(Constants.DME_PARAMS, params.toString())
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
                .setKeyAndValue(Constants.DME_RESPONSE, Objects.toString(response))
                .setKeyAndValue(Constants.DME_PARAMS, params.toString())
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
                                          String version,
                                          String envContext,
                                          String routeOffer,
                                          Integer timeout,
                                          String payload,
                                          String dmePartner,
                                          boolean isUseRouteOfferRequestHeaderFlag) {

        return new DME.Params(
                "POST",
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
}