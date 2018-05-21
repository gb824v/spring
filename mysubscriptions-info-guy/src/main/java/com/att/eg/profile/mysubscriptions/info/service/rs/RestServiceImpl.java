package com.att.eg.profile.mysubscriptions.info.service.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.service.ChannelsDmeProvider;

@Controller
public class RestServiceImpl implements RestService {
    private final YawlLogger yawllogger = new YawlLogger(RestServiceImpl.class);
    @Autowired
    private ChannelsDmeProvider channelsProvider;

    @Override
    public Response getChannels(HttpHeaders headers) {
        return channelsProvider.getChannels(headers);
    }
}
