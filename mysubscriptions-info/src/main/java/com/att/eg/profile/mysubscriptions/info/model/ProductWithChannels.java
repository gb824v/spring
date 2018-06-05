package com.att.eg.profile.mysubscriptions.info.model;

public class ProductWithChannels {
    private ChannelAsset[] channels;
    public ProductWithChannels(ChannelAsset[] channels) {
        this.channels = channels;
    }

    public ChannelAsset[] getChannels() {
        return channels;
    }
}
