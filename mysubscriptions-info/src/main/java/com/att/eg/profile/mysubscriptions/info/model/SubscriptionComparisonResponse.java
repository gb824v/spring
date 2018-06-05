package com.att.eg.profile.mysubscriptions.info.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionComparisonResponse {
    @JsonProperty("added")
    private ChannelAsset[] added;
    @JsonProperty("removed")
    private ChannelAsset[] removed;
    @JsonProperty("common")
    private ChannelAsset[] common;

    public ChannelAsset[] getAdded() {
        return this.added;
    }

    public void setAdded(ChannelAsset[] added) {
        this.added = added;
    }

    public ChannelAsset[] getRemoved() {
        return this.removed;
    }

    public void setRemoved(ChannelAsset[] removed) {
        this.removed = removed;
    }

    public ChannelAsset[] getCommon() {
        return this.common;
    }

    public void setCommon(ChannelAsset[] common) {
        this.common = common;
    }
}
