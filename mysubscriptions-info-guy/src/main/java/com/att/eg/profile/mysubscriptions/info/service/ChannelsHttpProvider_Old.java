package com.att.eg.profile.mysubscriptions.info.service;


import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@FunctionalInterface
public interface ChannelsHttpProvider_Old {
    Response getChannels(HttpHeaders headers);
}
