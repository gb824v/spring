package com.att.eg.profile.mysubscriptions.info.service;

import com.att.ajsc.common.exception.BadRequestException;
import com.att.eg.monitoring.annotations.statuscodes.CommonStatusCodes;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.adapters.dme.DMEAdapter;
import com.att.eg.profile.mysubscriptions.info.model.DMEParams;
import com.att.eg.profile.mysubscriptions.info.model.DMEResponseInfo;
import com.att.eg.profile.mysubscriptions.info.service.rs.RestServiceImpl;
import com.att.eg.profile.mysubscriptions.info.util.ConfigProperties;
import com.att.eg.profile.mysubscriptions.info.util.Constants;
import com.att.eg.profile.mysubscriptions.info.util.RBSYawlCodes;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.ClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Service
public class ChannelsHttpProviderImpl_Old implements ChannelsDmeProvider {
    private final YawlLogger yawllogger = new YawlLogger(RestServiceImpl.class);
    private ConfigProperties props;
    private Integer channelsTimeout;
    private Client client;

    public ChannelsHttpProviderImpl_Old(@Autowired ConfigProperties props,
                                        @Autowired Client client) {
        this.props = props;
        this.client = client;
    }

    @Override
    public Response getChannels(HttpHeaders headers) {
        Response response = null;
        StopWatch stopWatch = new StopWatch("My StopWatch");
        stopWatch.start();
        MultivaluedMap<String, String> reqHeaders = headers.getRequestHeaders();
        String accountToken = reqHeaders != null ? reqHeaders.getFirst(Constants.X_AEG_ACCOUNT_TOKEN) : null;
        channelsTimeout = props.getChannelsTimeout();
        try {
            validateAccountToken(accountToken);
            addClientTimeoutProperties();

            WebTarget target = client.target(props.getChannelsTarget()).path(props.getChannelsPath());
            response = target.request(MediaType.APPLICATION_JSON).put(Entity.json("{\"entitlements\": [],\"filters\": {\"subscription\": \"ALL\",\"channelType\": \"LINEAR\"}}"));

            String responseEntityString = response.readEntity(String.class);
        } catch (BadRequestException e) {
            yawllogger.debug(CommonStatusCodes.INVALID_INPUT_PARAMTERS,
                    new MetaBuilder().fromException(e, this.getClass().getSimpleName()).create());


        } catch (Exception e) {
        }
        stopWatch.stop();
        printRequestParamsForSeries(accountToken);
        return response;
    }

    private void printRequestParamsForSeries(String accountToken) {
        yawllogger.info(RBSYawlCodes.BOOKING_SERIES_REQ,
                new MetaBuilder().setKeyAndValue(Constants.ACCOUNT_TOKEN, accountToken)
                        .create());
    }


    private void validateAccountToken(String accountToken) {
        if (StringUtils.isBlank(accountToken)) {
            yawllogger.error(CommonStatusCodes.INVALID_INPUT_PARAMTERS, Constants.REASON,
                    "Bad Request: Missing account ID");
            throw new BadRequestException();
        }
    }

    private void addClientTimeoutProperties() {
        client.property(ClientProperties.CONNECT_TIMEOUT, channelsTimeout);
        client.property(ClientProperties.READ_TIMEOUT, channelsTimeout);
    }
}
