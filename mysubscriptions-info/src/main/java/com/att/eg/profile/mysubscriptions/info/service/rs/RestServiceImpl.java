package com.att.eg.profile.mysubscriptions.info.service.rs;

import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.model.Status;
import com.att.eg.profile.mysubscriptions.info.model.SubscriptionComparisonResponse;
import com.att.eg.profile.mysubscriptions.info.service.MySubscriptionsService;
import com.att.eg.profile.mysubscriptions.info.service.SubscriptionComparisonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.Response;

@Controller
public class RestServiceImpl implements RestService {
    private final YawlLogger log = new YawlLogger(RestServiceImpl.class);

    @Autowired
    private MySubscriptionsService service;

    private SubscriptionComparisonService subscriptionComparisonService;

    public RestServiceImpl(@Autowired SubscriptionComparisonService subscriptionComparisonService) {
        this.subscriptionComparisonService = subscriptionComparisonService;
    }

    @Override
    public Response getInfo(String profileId, String secureToken) {
        return service.getInfo(profileId, secureToken);
    }

    @Override
    public Response getCarousel(String profileId, String secureToken, String uxReference) {
        log.info(Status.OK, new MetaBuilder()
                .setReason("debug getCarousel:")
                .setKeyAndValue("profileId", profileId)
                .create());
        log.info(Status.OK, "profileId", profileId, "uxReference", uxReference);
        return service.getCarousel(profileId, secureToken, uxReference);
    }

    @Override
    public Response getSubscriptionComparison(String profileId, String secureToken, String routeOffer, String subscriptionId, String fisProperties) {
        log.info(Status.OK, new MetaBuilder()
                .setReason("debug getSubscriptionComparison:")
                .setKeyAndValue("profileId", profileId)
                .create());
        SubscriptionComparisonResponse response = subscriptionComparisonService.getSubscriptionComparison(profileId, secureToken, subscriptionId, routeOffer, fisProperties);
        return Response.ok(response).build();
    }

}
