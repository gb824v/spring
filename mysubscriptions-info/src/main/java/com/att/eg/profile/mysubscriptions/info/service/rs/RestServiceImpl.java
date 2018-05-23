package com.att.eg.profile.mysubscriptions.info.service.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.att.eg.monitoring.yawl.YawlLogger;

import com.att.eg.profile.mysubscriptions.info.common.dme.DME;
import com.att.eg.profile.mysubscriptions.info.model.Status;
import com.att.eg.profile.mysubscriptions.info.service.ChannelsDmeProvider;
import com.att.eg.profile.mysubscriptions.info.service.MySubscriptionsService;

@Controller
public class RestServiceImpl implements RestService {
    private final YawlLogger log = new YawlLogger(RestServiceImpl.class);

    @Autowired
    private MySubscriptionsService service;
    @Autowired
    private ChannelsDmeProvider channelsProvider;

    public RestServiceImpl() {
        // needed for autowiring
    }

    @Override
    public Response getInfo(String profileId, String secureToken) {
        return service.getInfo(profileId, secureToken);
    }

    @Override
    public Response getCarousel(String profileId, String secureToken, String uxReference) {
        log.info(Status.OK, "profileId", profileId, "uxReference", uxReference);
        return service.getCarousel(profileId, secureToken, uxReference);
    }

    @Override
    public DME.Response getChannels(HttpHeaders headers) {
        return channelsProvider.getChannels(headers);
    }
}
