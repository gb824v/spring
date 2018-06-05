package com.att.eg.profile.mysubscriptions.info.util;

import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.model.AddOn;
import com.att.eg.profile.mysubscriptions.info.model.BasePackageInfo;
import com.att.eg.profile.mysubscriptions.info.model.CDvrInfo;
import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsCarouselResponse;
import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsInfoResponse;
import com.att.eg.profile.mysubscriptions.info.model.Product;
import com.att.eg.profile.mysubscriptions.info.model.Resource;
import com.att.eg.profile.mysubscriptions.info.model.Status;
import com.att.eg.profile.mysubscriptions.info.model.Subscription;
import com.att.eg.profile.mysubscriptions.info.model.UxReference;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MySubscriptionsCarouselResponseBuilderImpl implements MySubscriptionsCarouselResponseBuilder {
    private final YawlLogger log = new YawlLogger(MySubscriptionsCarouselResponseBuilderImpl.class);

    private final MySubscriptionsInfoResponseBuilder infoResponseBuilder;
    private final CarouselItemConverter carouselItemConverter;
    private final ResponseBuilderUtil responseBuilderUtil;

    @Autowired
    public MySubscriptionsCarouselResponseBuilderImpl(MySubscriptionsInfoResponseBuilder infoResponseBuilder,
                                                      CarouselItemConverter carouselItemConverter,
                                                      ResponseBuilderUtil responseBuilderUtil) {
        this.infoResponseBuilder = infoResponseBuilder;
        this.carouselItemConverter = carouselItemConverter;
        this.responseBuilderUtil = responseBuilderUtil;
    }

    @Override
    public MySubscriptionsCarouselResponse buildResponse(String profileId, String secureToken, UxReference uxReference) {
        if(secureToken != null) {
            return buildResponseFromSecureToken(secureToken, uxReference);
        }
        return buildResponseFromProfileId(profileId, uxReference);
    }

    private MySubscriptionsCarouselResponse buildResponseFromSecureToken(String secureToken, UxReference uxReference) {
        MySubscriptionsCarouselResponse response = null;
        Map<String, Product> skuProductMap = responseBuilderUtil.getSkuProductMap();
        if(!skuProductMap.isEmpty()) {
            String sessionId = responseBuilderUtil.getQPSessionId(secureToken);
            if (sessionId != null) {
                Map<String, Subscription> skuSubscriptionMap = responseBuilderUtil.getSkuSubscriptionMap(sessionId);
                if(!skuSubscriptionMap.isEmpty()) {
                    List<Resource> resources = getResources(uxReference, skuProductMap, skuSubscriptionMap);
                    response = new MySubscriptionsCarouselResponse();
                    response.setResources(resources);
                } else {
                    //invalidate cache on failure
                    responseBuilderUtil.invalidateSessionCacheFor(secureToken);
                }
            } else {
                responseBuilderUtil.throwUnauthorizedException(this.getClass().getName());
            }
        }
        return response;
    }

    private List<Resource> getResources(UxReference uxReference, Map<String, Product> skuProductMap, Map<String, Subscription> skuSubscriptionMap) {
        String packageType = responseBuilderUtil.getPackageTypeByReference(uxReference);
        Collection<Product> products = skuProductMap.values().stream().sorted(Comparator.comparing(p -> Integer.valueOf(p.getDisplayOrder()))).collect(Collectors.toList());
        List<Product> filtered = filterProductsByPackageType(packageType, products);
        Stream<Resource> resources = filtered.stream().map(product -> carouselItemConverter.convertProductToResource(packageType, product, skuSubscriptionMap));
        if(CarouselItemConverter.PACKAGE_TYPE_ADDON.equals(packageType)) {
            resources = resources.filter(resource -> (Boolean) resource.getAdditionalProperties().get("isCurrent"));
        }
        return resources.collect(Collectors.toList());
    }

    private List<Product> filterProductsByPackageType(String packageType, Collection<Product> products) {
        return products.stream().filter(product -> packageType.equals(responseBuilderUtil.getProductPackageType(product))).collect(Collectors.toList());
    }

    private MySubscriptionsCarouselResponse buildResponseFromProfileId(String profileId, UxReference uxReference) {
        MySubscriptionsInfoResponse info = infoResponseBuilder.buildResponse(profileId, null);
        if(info == null) {
            return null;
        }
        MySubscriptionsCarouselResponse response = null;
        switch (uxReference) {
            case MYSUBSCRIPTIONS_CHANNELPACKAGESREFERENCE:
                List<BasePackageInfo> packagesList = new ArrayList<>();
                packagesList.add(info.getBasePackageInfo());
                response = convertBasePackagesToCarousel(info.getBasePackageInfo(), packagesList);
                break;
            case MYSUBSCRIPTIONS_ADDONSREFERENCE:
                List<AddOn> addOnsList = Arrays.asList(info.getAddOnsInfo().getAddOns());
                response = convertAddOnsToCarousel(addOnsList);
                break;
            case MYSUBSCRIPTIONS_TRUECLOUDSTORAGEREFERENCE:
                List<CDvrInfo> cDvrList = new ArrayList<>();
                cDvrList.add(info.getCDvrInfo());
                response = convertCDvrsToCarousel(info.getCDvrInfo(), cDvrList);
                break;
            default:
                log.info(Status.ERROR_GENERIC,
                                new MetaBuilder()
                                .setReason("Unsupported carousel type: " + uxReference)
                                .create());
                break;
        }
        return response;
    }

    private MySubscriptionsCarouselResponse convertCDvrsToCarousel(CDvrInfo current, List<CDvrInfo> cDvrList) {
        MySubscriptionsCarouselResponse response = new MySubscriptionsCarouselResponse();
        List<Resource> resources = response.getResources();
        for(CDvrInfo cDvrInfo : cDvrList) {
            boolean isCurrent = current == cDvrInfo;
            Resource resource = carouselItemConverter.convertCdvrInfoToResource(cDvrInfo, isCurrent);
            resources.add(resource);
        }
        return response;
    }

    private MySubscriptionsCarouselResponse convertAddOnsToCarousel(List<AddOn> addOnsList) {
        MySubscriptionsCarouselResponse response = new MySubscriptionsCarouselResponse();
        List<Resource> resources = response.getResources();
        for(AddOn addOn : addOnsList) {
            Resource resource = carouselItemConverter.convertAddOnToResource(addOn);
            resources.add(resource);
        }
        return response;
    }

    private MySubscriptionsCarouselResponse convertBasePackagesToCarousel(BasePackageInfo current, List<BasePackageInfo> packagesList) {
        MySubscriptionsCarouselResponse response = new MySubscriptionsCarouselResponse();
        List<Resource> resources = response.getResources();
        for(BasePackageInfo basePackageInfo : packagesList) {
            boolean isCurrent = current == basePackageInfo;
            Resource resource = carouselItemConverter.convertBasePackageToResource(basePackageInfo, isCurrent);
            resources.add(resource);
        }
        return response;
    }
}
