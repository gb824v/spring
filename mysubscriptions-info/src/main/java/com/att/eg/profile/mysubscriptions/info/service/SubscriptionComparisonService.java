package com.att.eg.profile.mysubscriptions.info.service;

import com.att.eg.profile.mysubscriptions.info.model.SubscriptionComparisonResponse;

public interface SubscriptionComparisonService { //NOSONAR this is NOT a functional interface
    SubscriptionComparisonResponse getSubscriptionComparison(String profileId, String secureToken, String packageId, String routeOffer, String fisProperties);
}
