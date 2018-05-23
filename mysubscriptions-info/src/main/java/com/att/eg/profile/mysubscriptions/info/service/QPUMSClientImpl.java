package com.att.eg.profile.mysubscriptions.info.service;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.att.eg.profile.mysubscriptions.info.model.ATSToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.att.eg.profile.mysubscriptions.info.model.ActiveSubscriptionsResponse;
import com.att.eg.profile.mysubscriptions.info.model.ProductsResponse;
import com.att.eg.profile.mysubscriptions.info.model.SessionResponse;

@Component
public class QPUMSClientImpl implements QPUMSClient {
	private static final Logger log = Logger.getLogger(QPUMSClientImpl.class.getName());

	private Client qpUms;

	private String qpUmsUrl;
	private String qpUmsGetProductsPath;
	private String qpUmsGetActiveSubscriptionsPath;
	private String qpUmsGetSessionPath;

	public QPUMSClientImpl(@Value("${qp.ums.url}") String qpUmsUrl,
								  @Value("${qp.ums.getProducts.path}") String qpUmsGetProductsPath,
								  @Value("${qp.ums.getActiveSubscriptions.path}") String qpUmsGetActiveSubscriptionsPath,
								  @Value("${qp.ums.getSession.path}") String qpUmsGetSessionPath,
						@Autowired Client qpUms) {
		this.qpUmsUrl = qpUmsUrl;
		this.qpUmsGetProductsPath = qpUmsGetProductsPath;
		this.qpUmsGetActiveSubscriptionsPath = qpUmsGetActiveSubscriptionsPath;
		this.qpUmsGetSessionPath = qpUmsGetSessionPath;
		this.qpUms = qpUms;
	}

	@Override
	public ProductsResponse getProducts() {
		WebTarget qpUmsGetProductsTarget = getWebTarget(qpUmsGetProductsPath);
		if (qpUmsGetProductsTarget == null) {
			return null;
		}

		Invocation.Builder builder = qpUmsGetProductsTarget.queryParam("salesChannel", SALES_CHANNEL)
			                                               .queryParam("apiKey", API_KEY).request(MediaType.APPLICATION_JSON);
		ProductsResponse response = builder.get(ProductsResponse.class);
		log.info(response.toString());
		return response;
	}


	@Override
	public ActiveSubscriptionsResponse getActiveSubscriptions(String sessionId) {
		WebTarget qpUmsGetActiveSubscriptionsTarget = getWebTarget(qpUmsGetActiveSubscriptionsPath);
		if (qpUmsGetActiveSubscriptionsTarget == null) {
			return null;
		}

		Invocation.Builder builder = qpUmsGetActiveSubscriptionsTarget.queryParam("sid", sessionId)
		                                                              .queryParam("apiKey", API_KEY).request(MediaType.APPLICATION_JSON);
		ActiveSubscriptionsResponse response = builder.get(ActiveSubscriptionsResponse.class);
		log.info(response.toString());
		return response;
	}

	@Override
	public SessionResponse getSession(String atsToken) {
		WebTarget qpUmsGetSessionTarget = getWebTarget(qpUmsGetSessionPath);
		if (qpUmsGetSessionTarget == null) {
			return null;
		}

		Invocation.Builder builder = qpUmsGetSessionTarget.request(MediaType.APPLICATION_JSON);
		SessionResponse response = builder.post(Entity.json(new ATSToken(atsToken)), SessionResponse.class);
		log.info(response.toString());
		return response;
	}

	private WebTarget getWebTarget(String path) {
		WebTarget qpUmsTarget = qpUms.target(qpUmsUrl);
		if (qpUmsTarget == null) {
			return null;
		}
		return qpUmsTarget.path(path);
	}
}
