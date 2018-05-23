package com.att.eg.profile.mysubscriptions.info.model;

import com.att.aft.dme2.handler.DME2RestfulHandler;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;

/**
 * Created by Annette.T on 9/10/2017.
 */
public class DMEResponseInfo {

    private String body;
    private Integer code;
    private Map<String, String> headers;

    public DMEResponseInfo(Integer code, String body, Map<String, String> headers) {
        this.body = body;
        this.code = code;
        this.headers = headers;
    }

    public DMEResponseInfo(DME2RestfulHandler.ResponseInfo responseInfo) {
        body = responseInfo.getBody();
        code = responseInfo.getCode();
        headers = responseInfo.getHeaders();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}


