package com.javacodegeeks.testng.spring.reset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
    @Value("${reset.accesstoken}")
    private String accesstoken;
    @Value("${reset.appid}")
    private String appid;

    @Value("${reset.authn_url}")
    private String authn_url;

    @Value("${reset.dng_host}")
    private String dng_host;

    public String getAccesstoken() {
        return accesstoken;
    }

    public String getAppid() {
        return appid;
    }

    public String getAuthn_url() {
        return authn_url;
    }

    public String getDng_host() {
        return dng_host;
    }
}