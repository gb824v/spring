package com.att.eg.profile.mysubscriptions.info.util;

import com.att.ajsc.common.discovery.internal.model.Channel;
import com.att.ajsc.common.discovery.internal.model.Entitlement;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.adapters.ChannelAdapter;
import com.att.eg.profile.mysubscriptions.info.adapters.ChannelImageMetadataAdapter;
import com.att.eg.profile.mysubscriptions.info.common.Constants;
import com.att.eg.profile.mysubscriptions.info.fis.config.ApplicationConfiguration;
import com.att.eg.profile.mysubscriptions.info.model.ChannelAsset;
import com.att.eg.profile.mysubscriptions.info.model.Product;
import com.att.eg.profile.mysubscriptions.info.model.ProductWithChannels;
import com.att.eg.profile.mysubscriptions.info.model.Status;
import com.att.eg.profile.mysubscriptions.info.service.SubscriptionComparisonServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductChannelsProviderImpl implements ProductChannelsProvider {

    private Map<String, ProductWithChannels> productWithChannelsMap = null;

    private final ChannelAdapter channelAdapter;
    private final ChannelImageMetadataAdapter channelImageMetadataAdapter;
    private final ResponseBuilderUtil responseBuilderUtil;

    @Autowired
    ApplicationConfiguration config;

    private final YawlLogger yawl = new YawlLogger(SubscriptionComparisonServiceImpl.class);

    @Autowired
    public ProductChannelsProviderImpl(ChannelAdapter channelAdapter,
                                       ChannelImageMetadataAdapter channelImageMetadataAdapter,
                                       ResponseBuilderUtil responseBuilderUtil) {
        this.channelAdapter = channelAdapter;
        this.channelImageMetadataAdapter = channelImageMetadataAdapter;
        this.responseBuilderUtil = responseBuilderUtil;
    }

    @Override
    public ProductWithChannels getProductWithChannels(String packageCode, String profileId, String routeOffer, String fisProperties) {
        if (productWithChannelsMap == null)
            populate(profileId, routeOffer, fisProperties);

        return productWithChannelsMap.get(packageCode);
    }

    private void populate(String profileId, String routeOffer, String fisProperties) {
        yawl.info(Status.OK, new MetaBuilder()
                .setNote("Populating product / channels map")
                .create());

        //1. GET ALL PACKAGES
        Map<String, Product> productMap = responseBuilderUtil.getSkuProductMap();

        //2. GET ALL CHANNELS
        Channel[] channels = channelAdapter.getChannels(profileId);

        //3. GET METADATA FOR EACH CHANNEL
        ChannelAsset[] channelAssets = channelImageMetadataAdapter.getAllChannelsMetaData(channels, profileId, routeOffer, fisProperties);

        //4. CREATE THE FULL PRODUCT MAP WITH CHANNELS
        createProductWithChannelsMap(channels, channelAssets, productMap);
    }

    private void createProductWithChannelsMap(Channel[] channels, ChannelAsset[] channelAssets, Map<String, Product> productMap) {
        String[] basePackageCodes = getBasePackageCodes(productMap);
        productWithChannelsMap = new HashMap<>();
        Arrays.stream(basePackageCodes).forEach(packageCode -> productWithChannelsMap.put(packageCode, new ProductWithChannels(getRelevantChannelAssets(packageCode, channels, channelAssets))));
    }

    private String[] getBasePackageCodes(Map<String, Product> productMap) {
        return productMap.values().stream()
                .filter(p -> p.getSku().startsWith(Constants.BASE_PRODUCT_PREFIX))
                .map(p -> PackageCodeUtil.convertFromSkuToPackageCode(p.getSku()))
                .toArray(String[]::new);
    }

    private ChannelAsset[] getRelevantChannelAssets(String packageCode, Channel[] channels, ChannelAsset[] channelAssets) {
        String packageCodeLowerCased = packageCode.toLowerCase();

        Channel[] filteredChannels = filterChannelsByPackageCode(channels, packageCodeLowerCased);

        //get equivalent channelAssets
        return filterChannelAssetsByResourceIds(channelAssets, filteredChannels);
    }

    private ChannelAsset[] filterChannelAssetsByResourceIds(ChannelAsset[] channelAssets, Channel[] filteredChannels) {
        return Arrays.stream(channelAssets)
                .filter(channelAsset -> Arrays.stream(filteredChannels).anyMatch(channel -> channel.getResourceId() == channelAsset.getResourceId()))
                .toArray(ChannelAsset[]::new);
    }

    private Channel[] filterChannelsByPackageCode(Channel[] channels, String packageCodeLowerCased) {
        return Arrays.stream(channels)
                .filter(channel -> containsProductName(packageCodeLowerCased, channel)).toArray(Channel[]::new);
    }

    private boolean containsProductName(String packageCodeLowerCased, Channel channel) {
        List<Entitlement> entitlements = channel.getEntitlements();
        return entitlements.stream().anyMatch(entitlement -> entitlement.getPcode().toLowerCase().contains(packageCodeLowerCased));
    }
}
