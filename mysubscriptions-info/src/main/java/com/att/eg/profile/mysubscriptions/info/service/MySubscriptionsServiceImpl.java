package com.att.eg.profile.mysubscriptions.info.service;

import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsCarouselResponse;
import com.att.eg.profile.mysubscriptions.info.model.UxReference;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsCarouselResponseBuilder;
import com.att.eg.profile.mysubscriptions.info.util.ResponseBuilderUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsInfoResponse;
import com.att.eg.profile.mysubscriptions.info.model.Status;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsInfoResponseBuilder;


@Service
public class MySubscriptionsServiceImpl implements MySubscriptionsService {
    private final YawlLogger log = new YawlLogger(MySubscriptionsServiceImpl.class);
    private MySubscriptionsInfoResponseBuilder infoResponseBuilder;
    private MySubscriptionsCarouselResponseBuilder carouselResponseBuilder;

    @Autowired
    public MySubscriptionsServiceImpl(MySubscriptionsInfoResponseBuilder infoResponseBuilder,
                                      MySubscriptionsCarouselResponseBuilder carouselResponseBuilder) {
        this.infoResponseBuilder = infoResponseBuilder;
        this.carouselResponseBuilder = carouselResponseBuilder;
    }

    @Override
    public Response getInfo(String profileId, String secureToken) {

        log.debug(Status.OK, "debug querying couchbase with profileId: ", profileId);

        if (ResponseBuilderUtil.isBlank(profileId)) {
            log.info(Status.ERROR_GENERIC,
                    new MetaBuilder()
                            .setReason("Profile Id is blank")
                            .create());
            //if no profile id given return Invalid Request Parameter response
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        MySubscriptionsInfoResponse info = infoResponseBuilder.buildResponse(profileId, secureToken);
        if (info == null) {
            return Response.noContent().build();
        }
        return Response.ok(info).build();
    }

    @Override
    public Response getCarousel(String profileId, String secureToken, String uxReference) {

        log.debug(Status.OK, "getCarousel() called with profileId: ", profileId, ", uxReference: ", uxReference);

        if (ResponseBuilderUtil.isBlank(profileId) || ResponseBuilderUtil.isBlank(uxReference)) {
            //if no profile id or ux reference given return Invalid Request Parameter response
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        UxReference eUxReference = UxReference.valueOf(uxReference.toUpperCase());
        MySubscriptionsCarouselResponse response = carouselResponseBuilder.buildResponse(profileId, secureToken, eUxReference);
        if (response == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(response).build();
    }
}