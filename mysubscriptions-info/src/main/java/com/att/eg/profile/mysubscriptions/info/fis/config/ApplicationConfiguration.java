package com.att.eg.profile.mysubscriptions.info.fis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    // ********* Thread Pool *************
    public static final Integer DEFAULT_THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2 + 1;

    @Value("${thread.pool.size}")
    Integer threadPoolSize;

    @Value("${fis.dme.target}")
    String fisTarget;// FIS Service End Point

    @Value("${fis.dme.path}")
    String fisPath;

    @Value("${fis.dme.version}")
    String fisVersion;

    @Value("${fis.dme.env.context}")
    String fisEnvContext;

    @Value("${fis.dme.timeout}")
    Integer fisTimeout;

    @Value("${fis.config.imageHost}")
    String fisImageHost;

    @Value("${fis.config.imageSpecDelimiter}")
    String fisImageSpecDelimiter;

    @Value("${fis.config.imageRequestIndicator}")
    String fisImageRequestIndicator;

    @Value("${fis.config.defaultId}")
    String fisDefaultId;

    @Value("${fis.retry}")
    Integer fisRetry;

    public Integer getThreadPoolSize() {
        if (threadPoolSize != null && threadPoolSize > 0) {
            return threadPoolSize;
        }
        return DEFAULT_THREAD_POOL_SIZE;
    }

    public String getFisTarget() {
        return fisTarget;
    }

    public String getFisPath() {
        return fisPath;
    }

    public String getFisVersion() {
        return fisVersion;
    }

    public String getFisEnvContext() {
        return fisEnvContext;
    }

    public Integer getFisTimeout() {
        return fisTimeout;
    }

    public String getFisImageHost() {
        return fisImageHost;
    }

    public String getFisImageSpecDelimiter() {
        return fisImageSpecDelimiter;
    }

    public String getFisImageRequestIndicator() {
        return fisImageRequestIndicator;
    }

    public String getFisDefaultId() {
        return fisDefaultId;
    }


    public Integer getFisRetry() {
        return fisRetry;
    }

}
