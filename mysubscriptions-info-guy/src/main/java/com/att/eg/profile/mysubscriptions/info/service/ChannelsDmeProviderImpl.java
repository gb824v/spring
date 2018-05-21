package com.att.eg.profile.mysubscriptions.info.service;

import com.att.ajsc.common.exception.BadRequestException;
import com.att.eg.monitoring.annotations.statuscodes.CommonStatusCodes;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.model.DMEParams;
import com.att.eg.profile.mysubscriptions.info.model.DMEResponseInfo;
import com.att.eg.profile.mysubscriptions.info.service.rs.RestServiceImpl;
import com.att.eg.profile.mysubscriptions.info.util.ConfigProperties;
import com.att.eg.profile.mysubscriptions.info.util.Constants;
import com.att.eg.profile.mysubscriptions.info.util.RBSYawlCodes;
import com.att.eg.profile.mysubscriptions.info.adapters.dme.DMEAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Service
public class ChannelsDmeProviderImpl implements ChannelsDmeProvider {
    private final YawlLogger yawllogger = new YawlLogger(RestServiceImpl.class);
    private ConfigProperties props;
    private DMEAdapter dmeAdapter;
    private Integer channelsTimeout;

    public ChannelsDmeProviderImpl(@Autowired ConfigProperties props,
                                   @Autowired DMEAdapter dmeAdapter) {
        this.props = props;
        this.dmeAdapter = dmeAdapter;
    }

    @Override
    public Response getChannels(HttpHeaders headers) {
        Response response = null;
        StopWatch stopWatch = new StopWatch("My StopWatch");
        stopWatch.start();
        MultivaluedMap<String, String> reqHeaders = headers.getRequestHeaders();
        String token = reqHeaders.getFirst("authorization");
        String accountToken = reqHeaders != null ? reqHeaders.getFirst(Constants.X_AEG_ACCOUNT_TOKEN) : null;
        channelsTimeout = props.getChannelsTimeout();
        try {

            String clientUri =
                    props.getChannelsTarget()
                            + props.getChannelsPath();
            Map<String, String> headersMap = new HashMap<String, String>();
            headersMap.put("authorization", token);
            String payload = "{\"entitlements\": [],\"filters\": {\"subscription\": \"ALL\",\"channelType\": \"LINEAR\"}}";
            DMEParams dmeParams = setDmeParams(clientUri, headersMap, payload);
            DMEResponseInfo responseInfo = dmeAdapter.callServiceWithPost(dmeParams);

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

    private DMEParams setDmeParams(String clientUri, Map<String, String> headers, String payload) {
        DMEParams dmeParams = new DMEParams(clientUri, headers, payload);
        dmeParams.setTimeoutInMilliseconds(props.getChannelsTimeout());
        dmeParams.setVersion(props.getChannelsVersion());
        return dmeParams;
    }
}