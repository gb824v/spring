package com.att.eg.profile.mysubscriptions.info.util;

import com.att.eg.monitoring.annotations.statuscodes.CommonStatusCodes;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class JsonUtils {

    private final YawlLogger log = new YawlLogger(JsonUtils.class);
    public static final ObjectMapper objMapper = new ObjectMapper();

    public JsonUtils() {
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T getObject(String json, Class<T> type) throws IOException {
        try {
            return objMapper.readValue(json, type);
        } catch (Exception e) {
            log.error(CommonStatusCodes.PARSING_FAILURE, "json", json, "exception", e);
            throw e;
        }
    }

    public String writeValueAsString(Object payload) {
        try {
            return objMapper.writeValueAsString(payload);
        } catch (Exception e) {
            log.error(CommonStatusCodes.PARSING_FAILURE, "exception", e);
        }
        return "";
    }
}
