package com.att.eg.profile.mysubscriptions.info.util;

import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsCarouselResponse;
import com.att.eg.profile.mysubscriptions.info.model.UxReference;

public interface MySubscriptionsCarouselResponseBuilder { // NOSONAR This is not a functional interface
    MySubscriptionsCarouselResponse buildResponse(String profileId, String secureToken, UxReference uxReference);
}
