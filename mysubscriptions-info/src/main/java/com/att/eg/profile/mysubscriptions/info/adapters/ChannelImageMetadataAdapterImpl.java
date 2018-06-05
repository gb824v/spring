package com.att.eg.profile.mysubscriptions.info.adapters;

import com.att.aft.dme2.internal.apache.commons.lang.StringUtils;
import com.att.ajsc.common.discovery.internal.model.Channel;
import com.att.ajsc.common.fis.model.FISImage;
import com.att.ajsc.common.fis.model.FISImageClass;
import com.att.ajsc.common.fis.model.FISSDKConfig;
import com.att.ajsc.common.fis.model.request.DMEPayload;
import com.att.ajsc.common.fis.model.request.FISDMEPayload;
import com.att.ajsc.common.fis.model.request.FISImageMetadataRequest;
import com.att.ajsc.common.fis.service.FISService;
import com.att.eg.profile.mysubscriptions.info.fis.config.ApplicationConfiguration;
import com.att.eg.profile.mysubscriptions.info.model.ChannelAsset;
import com.att.eg.profile.mysubscriptions.info.util.MDCExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

@Component
public class ChannelImageMetadataAdapterImpl implements ChannelImageMetadataAdapter {
    private MDCExecutorService executorService;
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    @Autowired
    ApplicationConfiguration config;

    @Autowired
    @Qualifier("dme2Filters")
    private Map<String, String> dme2Filters;

    @PostConstruct
    private void init() {
        executorService = new MDCExecutorService(config.getThreadPoolSize().intValue(), queue);
    }

    @Override
    public ChannelAsset[] getAllChannelsMetaData(Channel[] channels, String profileId, String routeOffer, String fisProperties) {
        Map<String, String> headers = new HashMap<>();

        if (dme2Filters != null)
            headers.putAll(dme2Filters);
        headers.put("X-AEG-Profile-ID", profileId);
        headers.put("X-AEG-Route-Offer", routeOffer);

        FISDMEPayload payload = createFISDMEPayload(config, headers);

        List<CompletableFuture<ChannelAsset>> futures = Arrays.stream(channels)
                .filter(Objects::nonNull)
                .map(channel -> convertToChannelAssestAndFillMetaDataAsync(channel, fisProperties, payload))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutureResult = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        allFutureResult.join();

        return futures.stream()
                .map(future -> CastToChannelAsset(future))
                .toArray(ChannelAsset[]::new);
    }

    private ChannelAsset CastToChannelAsset(CompletableFuture<ChannelAsset> futureChannelAsset) {
        return futureChannelAsset.join();
    }


    private FISDMEPayload createFISDMEPayload(ApplicationConfiguration config, Map<String, String> dme2Filters) {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.putAll(dme2Filters);
        FISSDKConfig fisConfig = new FISSDKConfig(
                config.getFisImageHost(),
                config.getFisImageSpecDelimiter(),
                config.getFisImageRequestIndicator(),
                config.getFisDefaultId());
        return new FISDMEPayload(
                config.getFisTarget(),
                config.getFisPath(),
                config.getFisVersion(),
                config.getFisEnvContext(),
                config.getFisTimeout(),
                filterMap,
                fisConfig);
    }

    private CompletableFuture<ChannelAsset> convertToChannelAssestAndFillMetaDataAsync(Channel channel, String fisProperties, FISDMEPayload payload) {
        CompletableFuture<ChannelAsset> cf = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> convertToChannelAssestAndFillMetaData(cf, channel, fisProperties, payload), executorService)
                .exceptionally(
                        throwable -> {
                            if (throwable != null) {
                            }
                            return null;
                        });
        return cf;
    }

    private void convertToChannelAssestAndFillMetaData(CompletableFuture<ChannelAsset> cf, Channel channel, String fisProperties, FISDMEPayload payload) {
        ChannelAsset channelAsset = mapChannelToChannelAsset(channel);
        loadImage(channelAsset, fisProperties, payload);
        cf.complete(channelAsset);
    }

    private void loadImage(ChannelAsset channel, String fisPropertyStr, DMEPayload payload) {
        if (StringUtils.isBlank(fisPropertyStr))
            return;

        FISImageMetadataRequest imageRequest = createImageMetadataRequest(channel.getResourceId(), fisPropertyStr);
        if (imageRequest != null) {
            // look up image in FIS
            try {
                List<FISImage> images = FISService.fetchImageMetadata(imageRequest, payload, config.getFisRetry(), true);
                channel.setImageList(images);

            } catch (Exception e) {
                String msg = e.getMessage();
            }

        }
    }

    private static FISImageMetadataRequest createImageMetadataRequest(String channelId, String fisPropertyStr) {
        if (!StringUtils.isBlank(channelId) && !StringUtils.isBlank(fisPropertyStr)) {

            return new FISImageMetadataRequest(
                    channelId,
                    fisPropertyStr,
                    null,//tmsId
                    FISImageClass.CHANNEL,
                    null,//series ResourceId
                    null//series tmsId
            );
        }
        return null;
    }


    private ChannelAsset mapChannelToChannelAsset(Channel channel) {
        ChannelAsset channelAsset = new ChannelAsset();
        channelAsset.setName(channel.getName());
        channelAsset.setResourceId(channel.getResourceId());
        return channelAsset;
    }
}
