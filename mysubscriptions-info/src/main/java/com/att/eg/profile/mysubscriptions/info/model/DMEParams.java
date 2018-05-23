package com.att.eg.profile.mysubscriptions.info.model;

import com.google.common.base.MoreObjects;

import java.util.Map;

/**
 * Created by Annette.T on 9/10/2017.
 */
public class DMEParams {
    private String clientUri;
    private Integer timeoutInMilliseconds;
    private String verb;
    private String urlContext;
    private Map<String, String> queryParams;
    private Map<String, String> headers;
    private String payload;
    private String version;
    private String environmentContext;
    private String routeOffer;
    boolean useReqHdrRteOfferFlg;
    private String partner;
    private boolean usingDefaultRoutOffer;


    public DMEParams() {
        //needed
    }

    public DMEParams(String clientUri, String urlContext, Map<String, String> queryParams,
                     Map<String, String> headers, String payload, boolean useReqHdrRteOfferFlg) {
        this.clientUri = clientUri;
        this.urlContext = urlContext;
        this.queryParams = queryParams;
        this.headers = headers;
        this.payload = payload;
        this.useReqHdrRteOfferFlg = useReqHdrRteOfferFlg;
    }

    public DMEParams(String clientUri, Map<String, String> headers, String payload) {
        this.clientUri = clientUri;
        this.headers = headers;
        this.payload = payload;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public boolean isUsingDefaultRoutOffer() {
        return usingDefaultRoutOffer;
    }

    public void setUsingDefaultRoutChannels(boolean usingDefaultRoutOffer) {
        this.usingDefaultRoutOffer = usingDefaultRoutOffer;
    }

    public String getEnvironmentContext() {
        return environmentContext;
    }

    public void setEnvironmentContext(String environmentContext) {
        this.environmentContext = environmentContext;
    }

    public String getRouteChannels() {
        return routeOffer;
    }

    public void setRouteChannels(String routeOffer) {
        this.routeOffer = routeOffer;
    }

    public String getClientUri() {
        return clientUri;
    }

    public void setClientUri(String clientUri) {
        this.clientUri = clientUri;
    }

    public Integer getTimeoutInMilliseconds() {
        return timeoutInMilliseconds;
    }

    public void setTimeoutInMilliseconds(Integer timeoutInMilliseconds) {
        this.timeoutInMilliseconds = timeoutInMilliseconds;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getUrlContext() {
        return urlContext;
    }

    public void setUrlContext(String urlContext) {
        this.urlContext = urlContext;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isUseReqHdrRteOfferFlg() {
        return useReqHdrRteOfferFlg;
    }

    public void setUseReqHdrRteOfferFlg(boolean useReqHdrRteOffrFlg) {
        this.useReqHdrRteOfferFlg = useReqHdrRteOffrFlg;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("clientUri", clientUri)
                .add("timeoutInMilliseconds", timeoutInMilliseconds)
                .add("verb", verb)
                .add("urlContext", urlContext)
                .add("queryParams", queryParams)
                .add("headers", headers)
                .add("payload", payload)
                .add("version", version)
                .add("environmentContext", environmentContext)
                .add("routeOffer", routeOffer)
                .add("useReqHdrRteOfferFlg", useReqHdrRteOfferFlg)
                .add("partner", partner)
                .add("usingDefaultRoutOffer", usingDefaultRoutOffer)
                .toString();
    }
}