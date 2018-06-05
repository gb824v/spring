package com.att.eg.profile.mysubscriptions.info.common.dme;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by vs5946 on 6/27/17.
 */
@FunctionalInterface
public interface DME {

    Response callService(Params params) throws Exception; // NOSONAR


    class Params {
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
        private String partner;
        boolean useRequestHeaderRouteOfferFlag;


        public Params(String verb, String clientUri, Integer timeoutInMilliseconds, String urlContext, //NOSONAR
                      Map<String, String> queryParams, Map<String, String> headers,
                      String payload, String version, String environmentContext, String routeOffer,
                      String partner, Boolean useRequestHeaderRouteOfferFlag) {
            this.clientUri = clientUri;
            this.timeoutInMilliseconds = timeoutInMilliseconds;
            this.urlContext = urlContext;
            this.queryParams = Objects.isNull(queryParams) ? new HashMap<>() : queryParams;
            this.headers = Objects.isNull(headers) ? new HashMap<>() : headers;
            this.payload = Objects.isNull(payload) ? "" : payload;
            this.version = version;
            this.environmentContext = environmentContext;
            this.routeOffer = routeOffer;
            this.verb = verb;
            this.partner = partner;
            this.useRequestHeaderRouteOfferFlag = useRequestHeaderRouteOfferFlag != null ? useRequestHeaderRouteOfferFlag.booleanValue() : false;
        }

        String getClientUri() {
            return clientUri;
        }

        Integer getTimeoutInMilliseconds() {
            return timeoutInMilliseconds;
        }

        String getVerb() {
            return verb;
        }

        String getUrlContext() {
            return urlContext;
        }

        public Map<String, String> getQueryParams() {
            return queryParams;
        }

        Map<String, String> getHeaders() {
            return headers;
        }

        String getPayload() {
            return payload;
        }

        String getVersion() {
            return version;
        }

        String getEnvironmentContext() {
            return environmentContext;
        }

        String getRouteOffer() {
            if (routeOffer == null) {
                return "";
            }
            return routeOffer;
        }

        String getPartner() {
            return partner;
        }

        boolean isUseRequestHeaderRouteOfferFlag() {
            return useRequestHeaderRouteOfferFlag;
        }

        @Override
        public String toString() {
            return "Params{" +
                    "clientUri='" + clientUri + '\'' +
                    ", timeoutInMilliseconds=" + timeoutInMilliseconds +
                    ", verb='" + verb + '\'' +
                    ", urlContext='" + urlContext + '\'' +
                    ", queryParams=" + queryParams +
                    ", headers=" + headers +
                    ", payload='" + payload + '\'' +
                    ", version='" + version + '\'' +
                    ", environmentContext='" + environmentContext + '\'' +
                    ", routeOffer='" + routeOffer + '\'' +
                    ", partner='" + partner + '\'' +
                    ", useRequestHeaderRouteOfferFlag=" + useRequestHeaderRouteOfferFlag +
                    '}';
        }
    }

    class Response {
        private String body;
        private Integer code;
        private Map<String, String> headers;

        public Response(Integer code, String body, Map<String, String> headers) {
            this.body = body;
            this.code = code;
            this.headers = headers;
        }

        public String getBody() {
            return body;
        }

        public Integer getCode() {
            return code;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }
    }
}