package com.att.eg.profile.mysubscriptions.info.util;

import com.att.eg.profile.mysubscriptions.info.model.AddOn;
import com.att.eg.profile.mysubscriptions.info.model.BasePackageInfo;
import com.att.eg.profile.mysubscriptions.info.model.CDvrInfo;
import com.att.eg.profile.mysubscriptions.info.model.Product;
import com.att.eg.profile.mysubscriptions.info.model.Resource;
import com.att.eg.profile.mysubscriptions.info.model.Subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CarouselItemConverter {

    public static final String RESOURCE_TYPE_PACKAGE = "PACKAGE";
    public static final String PACKAGE_TYPE_BASE = "BASE";
    public static final String PACKAGE_TYPE_ADDON = "ADDON";
    public static final String PACKAGE_TYPE_CDVR = "CDVR";

    public static final String FIELD_PACKAGE_TYPE = "packageType";
    public static final String FIELD_DISPLAY_NAME = "displayName";
    public static final String FIELD_CHANNEL_COUNT = "channelCount";
    public static final String FIELD_COLOR_CODE = "colorCode";
    public static final String FIELD_PRICE_USD = "priceUsd";
    public static final String FIELD_IS_CURRENT = "isCurrent";
    public static final String FIELD_AVAILABLE_HOURS = "availableHours";
    public static final String FIELD_CHANNEL_ID = "channelId";

    private ColorCodeBuilder colorCodeBuilder;
    private ResponseBuilderUtil responseBuilderUtil;

    @Autowired
    public CarouselItemConverter(ColorCodeBuilder colorCodeBuilder, ResponseBuilderUtil responseBuilderUtil) {
        this.colorCodeBuilder = colorCodeBuilder;
        this.responseBuilderUtil = responseBuilderUtil;
    }

    public Resource convertBasePackageToResource(BasePackageInfo basePackageInfo, boolean isCurrent) {
        Resource resource = new Resource();
        resource.setResourceType(RESOURCE_TYPE_PACKAGE);
        resource.setAdditionalProperty(FIELD_PACKAGE_TYPE, PACKAGE_TYPE_BASE);
        resource.setAdditionalProperty(FIELD_DISPLAY_NAME, basePackageInfo.getDisplayName());
        resource.setAdditionalProperty(FIELD_CHANNEL_COUNT, basePackageInfo.getChannelCount());
        resource.setAdditionalProperty(FIELD_COLOR_CODE, basePackageInfo.getColorCode());
        resource.setAdditionalProperty(FIELD_PRICE_USD, basePackageInfo.getPriceUsd());
        resource.setAdditionalProperty(FIELD_IS_CURRENT, isCurrent);
        return resource;
    }

    public Resource convertAddOnToResource(AddOn addOn) {
        Resource resource = new Resource();
        resource.setResourceType(RESOURCE_TYPE_PACKAGE);
        resource.setAdditionalProperty(FIELD_PACKAGE_TYPE, PACKAGE_TYPE_ADDON);
        resource.setAdditionalProperty(FIELD_DISPLAY_NAME, addOn.getDisplayName());
        resource.setAdditionalProperty(FIELD_CHANNEL_ID, addOn.getChannelId());
        resource.setAdditionalProperty(FIELD_PRICE_USD, addOn.getPriceUsd());
        return resource;
    }

    public Resource convertCdvrInfoToResource(CDvrInfo cDvrInfo, boolean isCurrent) {
        Resource resource = new Resource();
        resource.setResourceType(RESOURCE_TYPE_PACKAGE);
        resource.setAdditionalProperty(FIELD_PACKAGE_TYPE, PACKAGE_TYPE_CDVR);
        resource.setAdditionalProperty(FIELD_AVAILABLE_HOURS, cDvrInfo.getAvailableHours());
        resource.setAdditionalProperty(FIELD_PRICE_USD, cDvrInfo.getPriceUsd());
        resource.setAdditionalProperty(FIELD_IS_CURRENT, isCurrent);
        return resource;
    }

    public Resource convertProductToResource(String packageType, Product product, Map<String, Subscription> skuSubscriptionMap) {
        Subscription subscription = skuSubscriptionMap.get(product.getSku());
        switch(packageType) {
            case CarouselItemConverter.PACKAGE_TYPE_BASE:
                return convertBasePackageProductToResource(product, subscription);
            case CarouselItemConverter.PACKAGE_TYPE_ADDON:
                return convertAddOnProductToResource(product, subscription);
            case CarouselItemConverter.PACKAGE_TYPE_CDVR:
                return convertCdvrProductToResource(product, subscription);
            default:
                break;
        }
        return null;
    }

    private Resource convertCdvrProductToResource(Product product, Subscription subscription) {
        boolean isCurrent = responseBuilderUtil.isActive(subscription);
        String price = isCurrent ? subscription.getRetailPrice() : product.getPrice();
        Resource resource = new Resource();
        resource.setResourceType(RESOURCE_TYPE_PACKAGE);
        resource.setAdditionalProperty(FIELD_PACKAGE_TYPE, PACKAGE_TYPE_CDVR);
        resource.setAdditionalProperty(FIELD_AVAILABLE_HOURS, product.getStorageCapacity()+" Hours");
        resource.setAdditionalProperty(FIELD_PRICE_USD, price);
        resource.setAdditionalProperty(FIELD_IS_CURRENT, isCurrent);
        return resource;

    }

    private Resource convertAddOnProductToResource(Product product, Subscription subscription) {
        //TODO: do we return only active? Leaving as is until further clarification
        boolean isCurrent = subscription != null;
        String price = isCurrent ? subscription.getRetailPrice() : product.getPrice();
        Resource resource = new Resource();
        resource.setResourceType(RESOURCE_TYPE_PACKAGE);
        resource.setAdditionalProperty(FIELD_PACKAGE_TYPE, PACKAGE_TYPE_ADDON);
        resource.setAdditionalProperty(FIELD_DISPLAY_NAME, product.getDisplayName());
        resource.setAdditionalProperty(FIELD_CHANNEL_ID, 0);
        resource.setAdditionalProperty(FIELD_PRICE_USD, price);
        resource.setAdditionalProperty(FIELD_IS_CURRENT, isCurrent);
        return resource;

    }

    public Resource convertBasePackageProductToResource(Product product, Subscription subscription) {
        boolean isCurrent = responseBuilderUtil.isActive(subscription);
        String price = isCurrent ? subscription.getRetailPrice() : product.getPrice();
        String channelCount = responseBuilderUtil.parseChannelCount(product.getDescription());
        Resource resource = new Resource();
        resource.setResourceType(RESOURCE_TYPE_PACKAGE);
        resource.setAdditionalProperty(FIELD_PACKAGE_TYPE, PACKAGE_TYPE_BASE);
        resource.setAdditionalProperty(FIELD_DISPLAY_NAME, product.getDisplayName());
        resource.setAdditionalProperty(FIELD_CHANNEL_COUNT, channelCount);
        resource.setAdditionalProperty(FIELD_COLOR_CODE, colorCodeBuilder.buildColorCode(product.getDisplayName()));
        resource.setAdditionalProperty(FIELD_PRICE_USD, price);
        resource.setAdditionalProperty(FIELD_IS_CURRENT, isCurrent);
        return resource;
    }
}
