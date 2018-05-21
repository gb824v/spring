package com.att.eg.profile.mysubscriptions.info.adapters.dme;
import com.att.eg.profile.mysubscriptions.info.model.DMEParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rw961d on 8/22/2017.
 */
public class DMEparametersUtils {

	private DMEparametersUtils(){
		//Add a private constructor to hide the implicit public one
	}
	public static Map<String, String> getStringStringMap(List<String> filterParams) {
		Map<String, String> queryParams = new HashMap<>();
		for (String key : filterParams) {
			queryParams.put(key, "false");
		}
		return queryParams;
	}

	public static DMEParams getDmeParams(String clientUri, //NOSONAR
										 Map<String, String> queryParams,
										 String programVersion,
										 String programEnvironmentContext,
										 String programRouteChannels,
										 Integer timeout,
										 String payload,
										 boolean useReqHdrRteOfferFlg)  {
		DMEParams dmeParams = new DMEParams();
		dmeParams.setClientUri(clientUri);
		dmeParams.setTimeoutInMilliseconds(timeout);
		dmeParams.setUrlContext(null);
		dmeParams.setQueryParams(queryParams);
		dmeParams.setHeaders(null);
		dmeParams.setVersion(programVersion);
		dmeParams.setEnvironmentContext(programEnvironmentContext);
		dmeParams.setRouteChannels(programRouteChannels);
		dmeParams.setPayload(payload);
		dmeParams.setUseReqHdrRteOfferFlg(useReqHdrRteOfferFlg);
		return dmeParams;
	}
}
