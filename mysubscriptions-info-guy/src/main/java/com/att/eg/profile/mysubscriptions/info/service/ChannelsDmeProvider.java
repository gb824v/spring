package com.att.eg.profile.mysubscriptions.info.service;


import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@FunctionalInterface
public interface ChannelsDmeProvider {
    Response getChannels(HttpHeaders httpHeaders);
}
