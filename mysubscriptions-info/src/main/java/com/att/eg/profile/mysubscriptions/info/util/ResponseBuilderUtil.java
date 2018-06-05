package com.att.eg.profile.mysubscriptions.info.util;

import com.att.ajsc.common.exception.UnauthorizedException;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.model.ActiveSubscriptionsResponse;
import com.att.eg.profile.mysubscriptions.info.model.Product;
import com.att.eg.profile.mysubscriptions.info.model.ProductsResponse;
import com.att.eg.profile.mysubscriptions.info.model.Session;
import com.att.eg.profile.mysubscriptions.info.model.SessionResponse;
import com.att.eg.profile.mysubscriptions.info.model.SessionResponseData;
import com.att.eg.profile.mysubscriptions.info.model.Status;
import com.att.eg.profile.mysubscriptions.info.model.Subscription;
import com.att.eg.profile.mysubscriptions.info.model.SubscriptionData;
import com.att.eg.profile.mysubscriptions.info.model.UxReference;
import com.att.eg.profile.mysubscriptions.info.service.QPUMSClient;
import com.couchbase.client.java.util.LRUCache;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseBuilderUtil {

    private static final String STATUS_ACTIVE = "Active";
    private final YawlLogger log = new YawlLogger(ResponseBuilderUtil.class);

    //TODO: switch to use CouchBase for caching
    private static final LRUCache<String, String> sessionCache = new LRUCache(1000);
    private static final LRUCache<String, Product> productsCache = new LRUCache<>(100);

    private QPUMSClient qpumsClient;

    public ResponseBuilderUtil(@Autowired QPUMSClient qpumsClient) {
        this.qpumsClient = qpumsClient;
    }

    public String getQPSessionId(String secureToken) {
        String cachedId = sessionCache.get(secureToken);
        if(cachedId != null) {
            return cachedId;
        }
        SessionResponse response = qpumsClient.getSession(secureToken);
        String sessionId = parseSessionId(response);
        if(sessionId != null) {
            sessionCache.put(secureToken, sessionId);
        }
        return sessionId;
    }

    private String parseSessionId(SessionResponse response) {
        if(response != null) {
            SessionResponseData data = response.getData();
            if (data != null) {
                Session session = data.getSession();
                if (session != null) {
                    return session.getId();
                }
            }
        }
        return null;
    }

    public static boolean isBlank(String value) {
        return value == null || value.isEmpty();
    }

    public Map<String, Product> getSkuProductMap() {
        if(productsCache.isEmpty()) {
            ProductsResponse productsResponse = qpumsClient.getProducts();
            if(productsResponse != null) {
                List<Product> products = productsResponse.getData();
                for (Product product : products) {
                    productsCache.put(product.getSku(), product);
                }
            }
        }
        return productsCache;
    }

    public Map<String, Subscription> getSkuSubscriptionMap(String sessionId) {
        Map<String, Subscription> skuSubscriptionMap = new HashMap<>();
        ActiveSubscriptionsResponse activeSubscriptionsResponse = qpumsClient.getActiveSubscriptions(sessionId);
        if(activeSubscriptionsResponse != null) {
            SubscriptionData data = activeSubscriptionsResponse.getData();
            if(data != null) {
                List<Subscription> subscriptions = data.getSubscriptions();
                populateSkuSubscriptionsMap(skuSubscriptionMap, subscriptions);
            }
        }
        return skuSubscriptionMap;
    }

    private void populateSkuSubscriptionsMap(Map<String, Subscription> skuSubscriptionMap, List<Subscription> subscriptions) {
        if(subscriptions != null) {
            for (Subscription subscription : subscriptions) {
                skuSubscriptionMap.put(subscription.getSku(), subscription);
            }
        }
    }

    public boolean isActive(Subscription subscription) {
        return subscription != null && STATUS_ACTIVE.equals(subscription.getStatus());
    }

    public String getPackageTypeByReference(UxReference uxReference) {
        switch(uxReference) {
            case MYSUBSCRIPTIONS_CHANNELPACKAGESREFERENCE:
                return CarouselItemConverter.PACKAGE_TYPE_BASE;
            case MYSUBSCRIPTIONS_ADDONSREFERENCE:
                return CarouselItemConverter.PACKAGE_TYPE_ADDON;
            case MYSUBSCRIPTIONS_TRUECLOUDSTORAGEREFERENCE:
                return CarouselItemConverter.PACKAGE_TYPE_CDVR;
            default:
                break;
        }
        return "";
    }

    public String getProductPackageType(Product product) {
        String packageType = "";
        if(product != null) {
            if(product.isBasicService()) {
                return CarouselItemConverter.PACKAGE_TYPE_BASE;
            } else {
                String category = product.getCategory();
                switch (category) {
                    case "Subscription Channels":
                        return CarouselItemConverter.PACKAGE_TYPE_ADDON;
                    case "DVR":
                        return CarouselItemConverter.PACKAGE_TYPE_CDVR;
                    default:
                        break;
                }
            }
        }
        return packageType;
    }

    public void invalidateSessionCacheFor(String secureToken) {
        log.warn(Status.ERROR_SESSION_EXPIRED,
                new MetaBuilder()
                        .setReason("QP UMS Session seems like expired for this token, invalidating cache")
                        .create());
        sessionCache.put(secureToken, null);
    }


    public void throwUnauthorizedException(String callingClassName) {
        RuntimeException e = new UnauthorizedException();
        log.warn(Status.ERROR_TOKEN_EXPIRED,
                new MetaBuilder()
                        .setReason("Was not able to obtain session QP UMS session using this token")
                        .fromException(e, callingClassName)
                        .create());
        throw e;
    }

    public String parseChannelCount(String description) {
        if(description != null) {
            Pattern pattern = Pattern.compile("##(\\d+)##");
            Matcher matcher = pattern.matcher(description);
            if (matcher.find()) {
                return matcher.group(1) + "+ Live Channels";
            }
        }
        return null;
    }
}
