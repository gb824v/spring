package com.att.eg.profile.mysubscriptions.info.service;

import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.common.Constants;
import com.att.eg.profile.mysubscriptions.info.model.*;
import com.att.eg.profile.mysubscriptions.info.util.ProductChannelsProvider;
import com.att.eg.profile.mysubscriptions.info.util.YawlCodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionComparisonServiceImpl implements SubscriptionComparisonService {

    private final ProductChannelsProvider productChannelsProvider;
    private final YawlLogger yawl = new YawlLogger(SubscriptionComparisonServiceImpl.class);

    public SubscriptionComparisonServiceImpl(@Autowired ProductChannelsProvider productChannelsProvider) {
        this.productChannelsProvider = productChannelsProvider;
    }

    @Override
    public SubscriptionComparisonResponse getSubscriptionComparison(String profileId, String secureToken, String subscriptionId, String routeOffer, String fisProperties) {

        yawl.info(YawlCodes.GET_SUBSCRIPTION_COMPARISON,
                new MetaBuilder()
                        .setKeyAndValue(Constants.PROFILE_ID, profileId)
                        .setKeyAndValue(Constants.SECURE_TOKEN, secureToken)
                        .setKeyAndValue(Constants.SUBSCRIPTION_ID, subscriptionId)
                        .setKeyAndValue(Constants.ROUTE_OFFER, routeOffer)
                        .setKeyAndValue(Constants.FISPROPERTIES, fisProperties)
                        .create());

        ProductWithChannels product = productChannelsProvider.getProductWithChannels(subscriptionId, profileId, routeOffer, fisProperties);
        return createDummyResponse(product.getChannels());
    }

    private SubscriptionComparisonResponse createDummyResponse(ChannelAsset[] channels) {
        SubscriptionComparisonResponse response = new SubscriptionComparisonResponse();

        if (channels != null && channels.length > 0) {
            response.setAdded(channels);
            response.setRemoved(channels);
            response.setCommon(channels);
            return response;
        }

        return response;
    }
}
