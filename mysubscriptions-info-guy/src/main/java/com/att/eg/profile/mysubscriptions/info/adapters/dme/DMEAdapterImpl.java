package com.att.eg.profile.mysubscriptions.info.adapters.dme;

import com.att.aft.dme2.api.DME2Exception;
import com.att.ajsc.dme2.handler.DME2RestfulHandlerWrapper;
import com.att.eg.monitoring.annotations.statuscodes.CommonStatusCodes;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.model.DMEParams;
import com.att.eg.profile.mysubscriptions.info.model.DMEResponseInfo;
import com.att.eg.profile.mysubscriptions.info.util.RBSYawlCodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Annette.T on 9/10/2017.
 */
@Component public class DMEAdapterImpl implements DMEAdapter
{

	private static final YawlLogger yawl = new YawlLogger(DMEAdapterImpl.class);

	@Autowired @Qualifier("dme2Filters") private Map<String, String> dme2Filters;

	@Override public DMEResponseInfo callServiceWithPost(DMEParams dmeParams) throws DME2Exception
	{
		if (dmeParams.getHeaders() == null)
		{
			dmeParams.setHeaders(new HashMap<>());
		}
		dmeParams.getHeaders().put("Content-Type", "application/json");

		dmeParams.setVerb("POST");

		return callService(dmeParams);
	}

	@Override public DMEResponseInfo callServiceWithPut(DMEParams dmeParams) throws DME2Exception
	{
		if (dmeParams.getHeaders() == null)
		{
			dmeParams.setHeaders(new HashMap<>());
		}
		dmeParams.getHeaders().put("Content-Type", "application/json");

		dmeParams.setVerb("PUT");

		return callService(dmeParams);
	}

	@Override public DMEResponseInfo callServiceWithGet(DMEParams dmeParams) throws DME2Exception
	{
		dmeParams.setVerb("GET");
		return callService(dmeParams);
	}

	@Override public DMEResponseInfo callServiceWithDelete(DMEParams dmeParams) throws DME2Exception
	{
		dmeParams.setVerb("DELETE");
		return callService(dmeParams);
	}

	private DMEResponseInfo callService(DMEParams params) throws DME2Exception
	{
		String queryParams = "";
		if (params.getQueryParams() != null)
		{
			queryParams = params.getQueryParams().toString();
		}
		String payLoad = "";
		if (params.getPayload() != null)
		{
			payLoad = params.getPayload();
		}

		String headers = new String();
		if (params.getHeaders() != null)
		{
			headers = params.getHeaders().toString();
		}

		String clientUriFull = null;
		try
		{
			if (params.getHeaders() == null)
			{
				params.setHeaders(new HashMap<>());
			}

			params.getHeaders().putAll(dme2Filters);

			clientUriFull = params.getClientUri() + "?" + "version=" + params.getVersion() + "&envContext=" + params
					.getEnvironmentContext() + (params.isUsingDefaultRoutOffer() ?
					"&partner=" + params.getPartner() :
					"&routeOffer=" + params.getRouteChannels());

			yawl.debug(CommonStatusCodes.INFORMATIONAL,
					new MetaBuilder().setKeyAndValue("fullClientURI", clientUriFull).setKeyAndValue("Payload", payLoad)
							.setKeyAndValue("Query Params", queryParams).setKeyAndValue("header Params", headers)
							.create());

			return new DMEResponseInfo(DME2RestfulHandlerWrapper
					.callService(clientUriFull, params.getTimeoutInMilliseconds(), params.getVerb(),
							params.getUrlContext(), params.getQueryParams(), params.getHeaders(), params.getPayload(),
							params.isUseReqHdrRteOfferFlg()));
		}
		catch (Exception ex)
		{
			yawl.error(RBSYawlCodes.EXCEPTION_DME_CALL,
					new MetaBuilder().setNote(ex.getMessage()).setReason(ex.getMessage())
							.fromException(ex, this.getClass().getName()).setStackTrace(ex)
							.setKeyAndValue("fullClientURI", clientUriFull).setKeyAndValue("Payload", payLoad)
							.setKeyAndValue("Query Params", queryParams).setKeyAndValue("header Params", headers)
							.create());
			throw new DME2Exception("DME2", "Failed to call service", ex);
		}
	}
}