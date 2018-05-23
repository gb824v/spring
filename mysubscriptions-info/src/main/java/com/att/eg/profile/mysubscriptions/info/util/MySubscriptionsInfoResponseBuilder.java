package com.att.eg.profile.mysubscriptions.info.util;

import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsInfoResponse;

public interface MySubscriptionsInfoResponseBuilder{// NOSONAR This is not a functional interface
	MySubscriptionsInfoResponse buildResponse(String profileId, String secureToken);
}
