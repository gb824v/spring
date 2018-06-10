package com.att.testng.spring.reset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:application.properties")
@Configuration
public class ApplicationConfiguration {

    @Value("${rest.dev_url}")
    private String dev_url;
    @Value("${rest.stage_url}")
    private String stage_url;
    @Value("${rest.tgHost}")
    private String tgHost;
    @Value("${rest.tgAppId}")
    private String tgAppId;
    @Value("${rest.tgPort}")
    private String tgPort;
    @Value("${rest.tgUrl}")
    private String tgUrl;
    @Value("${rest.user}")
    private String user;
    @Value("${rest.password}")
    private String password;
    @Value("${rest.tgop}")
    private String tgop;
    @Value("${rest.tgRememberId}")
    private String tgRememberId;
    @Value("${rest.authNPort}")
    private String authNPort;
    @Value("${rest.euthNClientId}")
    private String euthNClientId;
    @Value("${rest.restType}")
    private String restType;



    public String getDev_url() {
        return dev_url;
    }

    public String getStage_url() {
        return stage_url;
    }

    public String getTgHost() {
        return tgHost;
    }

    public String getTgAppId() {
        return tgAppId;
    }

    public String getTgPort() {
        return tgPort;
    }

    public String getTgUrl() {
        return tgUrl;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getTgop() {
        return tgop;
    }

    public String getTgRememberId() {
        return tgRememberId;
    }

    public String getAuthNPort() {
        return authNPort;
    }

    public String getEuthNClientId() {
        return euthNClientId;
    }

    public String getRestType() {
        return restType;
    }
}