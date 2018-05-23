package com.att.eg.profile.mysubscriptions.info.service;

import com.att.eg.profile.mysubscriptions.info.model.ActiveSubscriptionsResponse;
import com.att.eg.profile.mysubscriptions.info.model.ProductsResponse;
import com.att.eg.profile.mysubscriptions.info.model.SessionResponse;

public interface QPUMSClient {
	static final String API_KEY = "qwerty";
	static final String SALES_CHANNEL = "Residential";
	static final String ATS_TOKEN = "DA:1524842238026:4AoxsvuYb4MiPyS2RePRfdI6yKM";
	ProductsResponse getProducts();
	ActiveSubscriptionsResponse getActiveSubscriptions(String sessionId);
	SessionResponse getSession(String atsToken);
}