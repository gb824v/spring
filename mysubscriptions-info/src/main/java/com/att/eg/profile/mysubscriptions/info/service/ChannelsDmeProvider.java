package com.att.eg.profile.mysubscriptions.info.service;


import com.att.eg.profile.mysubscriptions.info.common.dme.DME;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@FunctionalInterface
public interface ChannelsDmeProvider {
    DME.Response getChannels(HttpHeaders httpHeaders);
}
