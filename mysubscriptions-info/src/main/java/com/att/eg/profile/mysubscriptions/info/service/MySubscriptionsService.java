package com.att.eg.profile.mysubscriptions.info.service;

import javax.ws.rs.core.Response;

public interface MySubscriptionsService {		// NOSONAR This is not a functional interface
	Response getInfo(String profileId, String secureToken);
	Response getCarousel(String profileId, String secureToken, String uxReference);
}
