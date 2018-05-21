package com.att.eg.profile.mysubscriptions.info.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Annette.T on 9/10/2017.
 */
@Component
public class ConfigProperties {

    @Value("${channels.target}")
    private String channelsTarget;

    @Value("${channels.path}")
    private String channelsPath;

    @Value("${channels.timeout}")
    private Integer channelsTimeout;

    @Value("${channels.version}")
    private String channelsVersion;

    public String getChannelsTarget() {
        return channelsTarget;
    }

    public void setChannelsTarget(String channelsTarget) {
        this.channelsTarget = channelsTarget;
    }

    public Integer getChannelsTimeout() {
        return channelsTimeout;
    }

    public void setChannelsTimeout(Integer channelsTimeout) {
        this.channelsTimeout = channelsTimeout;
    }

    public String getChannelsVersion() {
        return channelsVersion;
    }

    public void setChannelsVersion(String channelsVersion) {
        this.channelsVersion = channelsVersion;
    }

    public String getChannelsPath() {
        return channelsPath;
    }

    public void setChannelsPath(String channelsPath) {
        this.channelsPath = channelsPath;
    }
}
