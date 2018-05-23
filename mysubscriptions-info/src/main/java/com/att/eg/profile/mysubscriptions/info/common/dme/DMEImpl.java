package com.att.eg.profile.mysubscriptions.info.common.dme;

import com.att.aft.dme2.handler.DME2RestfulHandler;
import com.att.ajsc.dme2.handler.DME2RestfulHandlerWrapper;
import com.att.eg.monitoring.annotations.statuscodes.CommonStatusCodes;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.common.Constants;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by vs5946 on 6/27/17.
 */
@Component
public class DMEImpl implements DME {

	public static final YawlLogger yawl = new YawlLogger(DMEImpl.class);

	private Map<String, String> dme2Filters;

	DMEImpl(@Qualifier("dme2Filters") Map<String, String> dme2Filters) {
		this.dme2Filters = dme2Filters;
	}


	@Override
	public Response callService(Params params) throws Exception {

		params.getHeaders().putAll(dme2Filters);

		String routOffer = params.getRouteOffer();
		String clientUriFull = params.getClientUri() + "?"
				+ "version="
				+ params.getVersion()
				+ "&envContext="
				+ params.getEnvironmentContext()
				+ (routOffer.isEmpty() ? "&partner=" + params.getPartner() : "&routeOffer=" + routOffer);


		yawl.debug(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
				.setNote("Sending Call")
				.setKeyAndValue(Constants.DME_PARAMS, params.toString())
				.setKeyAndValue("Full_URL", clientUriFull)
				.create());

		DME2RestfulHandler.ResponseInfo responseInfo = DME2RestfulHandlerWrapper.callService(
				clientUriFull,
				params.getTimeoutInMilliseconds(),
				params.getVerb(),
				params.getUrlContext(),
				params.getQueryParams(),
				params.getHeaders(),
				params.getPayload(),
				params.isUseRequestHeaderRouteOfferFlag());

		return new Response(responseInfo.getCode(), responseInfo.getBody(), responseInfo.getHeaders());
	}
}
