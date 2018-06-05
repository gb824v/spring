package com.att.eg.profile.mysubscriptions.info.util;

import com.att.eg.profile.mysubscriptions.info.model.ProductWithChannels;

public interface ProductChannelsProvider { //NOSONAR
    ProductWithChannels getProductWithChannels(String packageCode, String profileId, String routeOffer, String fisProperties);
}
