package com.att.eg.profile.mysubscriptions.info.adapters;

import com.att.ajsc.common.discovery.internal.model.Channel;
import com.att.ajsc.common.discovery.internal.model.rest.ChannelResponse;
import com.att.eg.monitoring.annotations.statuscodes.CommonStatusCodes;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.common.Constants;
import com.att.eg.profile.mysubscriptions.info.common.dme.DME;
import com.att.eg.profile.mysubscriptions.info.common.FailureException;
import com.att.eg.profile.mysubscriptions.info.common.MySubscriptionsInfoErrorType;
import com.att.eg.profile.mysubscriptions.info.common.MySubscriptionsInfoStatusCodes;
import com.att.eg.profile.mysubscriptions.info.util.JsonUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.Response;

@Service
public class ChannelsAdapterImpl implements ChannelAdapter {
    private final YawlLogger yawl = new YawlLogger(ChannelsAdapterImpl.class);
    private DME dme;
    private Integer timeout;
    private String target;
    private String path;
    private String version;
    private String routeOffer;
    private String envContext;
    private String dmePartner;
    private boolean isUseRouteOfferRequestHeaderFlag;
    private JsonUtils jsonUtils;
    private ChannelsAdapterImpl(@Value("${channels.dme.target}") String target,//NOSONAR
                                @Value("${channels.dme.path}") String path,
                                @Value("${channels.dme.timeout}") Integer timeout,
                                @Value("${channels.dme.version}") String version,
                                @Value("${channels.dme.env.context}") String envContext,
                                @Value("${channels.dme.routeOffer}") String routeOffer,
                                @Value("${channels.dme.partner}") String dmePartner,
                                @Value("${channels.dme.use.route.offer.request.header}") Boolean isUseRouteOfferRequestHeaderFlag,
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
        this.jsonUtils = new JsonUtils();
    }

    public LoadingCache<Integer, Channel[]> buildChannelsFromCache(String profileId) {
        LoadingCache<Integer, Channel[]> empCache;

        empCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<Integer, Channel[]>() {
                    @Override
                    public Channel[] load(Integer id) throws Exception {
                        return getChannels(profileId);
                    }
                });
        return empCache;
    }

    public Channel[] getChannelsFrom(String profileId) {
        LoadingCache<Integer, Channel[]> empCache = buildChannelsFromCache(profileId);
        try {
            return empCache.get(0);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class Payload {
        public HashMap<String, String> filters;

        public Payload() {
            filters = new HashMap<>();
            filters.put("subscription", "ALL");
            filters.put("channelType", "LINEAR");
        }
    }

    @Override
    public Channel[] getChannels(String profileId) {
        ChannelResponse channelResponse = null;
        String payload = "";
        String clientURI = Constants.HTTPS_PREFIX + target + path;
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json");
        payload = jsonUtils.writeValueAsString(new Payload());
        DME.Params dmeParams = getDmeParams(clientURI, null, headersMap, Constants.POST, version, envContext,
                routeOffer, timeout, payload, dmePartner, isUseRouteOfferRequestHeaderFlag);
        DME.Response response = callService(dmeParams);
        List<Channel> channelsList = null;
        if (response != null) {
            channelResponse = parseResponse(response.getBody());
            channelsList = channelResponse != null ? channelResponse.getChannels() : null;
        }

        Channel[] channels = new Channel[channelsList.size()];
        channels = channelsList.toArray(channels);
        return channels;
    }

    private ChannelResponse parseResponse(String body) {
        ChannelResponse channelResponse;
        try {
            channelResponse = jsonUtils.getObject(body, ChannelResponse.class);
        } catch (Exception e) {
            yawl.error(CommonStatusCodes.PARSING_FAILURE,
                    new MetaBuilder().setReason(e.getMessage()).setNote("Could not serialize channels")
                            .setKeyAndValue(com.att.eg.profile.mysubscriptions.info.common.Constants.BODY, body).fromException(e, this.getClass().getName()).create());
            throw new FailureException(MySubscriptionsInfoErrorType.PROGRAM_PARSE_FAILURE,
                    "Could not serialize Program Offerings");
        }
        return channelResponse;
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
            yawl.error(MySubscriptionsInfoStatusCodes.MULTI_PROGRAMS_DETAILS_FETCH_ERROR,
                    new MetaBuilder().setReason(e.getMessage()).setNote("").fromException(e, this.getClass().getName())
                            .setKeyAndValue(com.att.eg.profile.mysubscriptions.info.common.Constants.DME_PARAMS, params.toString()).create());
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
        yawl.error(MySubscriptionsInfoStatusCodes.INVALID_PROGRAM_OFFERING_RESPONSE,
                new MetaBuilder().setNote("program response failure invalid status")
                        .setKeyAndValue(com.att.eg.profile.mysubscriptions.info.common.Constants.DME_RESPONSE, Objects.toString(response))
                        .setKeyAndValue(com.att.eg.profile.mysubscriptions.info.common.Constants.DME_PARAMS, params.toString()).create());

        if (response.getCode() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw new FailureException(MySubscriptionsInfoErrorType.CANONICAL_ITEMS_NOT_FOUND,
                    "canonical items not found");
        } else {
            throw new FailureException(MySubscriptionsInfoErrorType.PROGRAM_FETCH_FAILURE,
                    "response code: " + response.getCode());
        }
    }

    public static DME.Params getDmeParams(String clientUri, //NOSONAR
                                          Map<String, String> queryParams, Map<String, String> headers, String method, String version,
                                          String envContext, String routeOffer, Integer timeout, String payload, String dmePartner,
                                          boolean isUseRouteOfferRequestHeaderFlag) {

        return new DME.Params(method, clientUri, timeout, null, queryParams, headers, payload, version, envContext,
                routeOffer, dmePartner, isUseRouteOfferRequestHeaderFlag);
    }
}