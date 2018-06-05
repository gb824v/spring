package com.att.eg.profile.mysubscriptions.info.adapters;

import com.att.ajsc.common.discovery.internal.model.Channel;
import com.att.eg.profile.mysubscriptions.info.model.ChannelAsset;

public interface ChannelImageMetadataAdapter {
    ChannelAsset[] getAllChannelsMetaData(Channel[] channels, String profileId, String routeOffer, String fisProperties);
}
