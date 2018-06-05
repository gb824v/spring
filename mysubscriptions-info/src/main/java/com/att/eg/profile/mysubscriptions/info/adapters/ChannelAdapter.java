package com.att.eg.profile.mysubscriptions.info.adapters;


import com.att.ajsc.common.discovery.internal.model.Channel;

import javax.ws.rs.core.Response;

@FunctionalInterface
public interface ChannelAdapter {
    Channel[] getChannels(String profileId);
}
