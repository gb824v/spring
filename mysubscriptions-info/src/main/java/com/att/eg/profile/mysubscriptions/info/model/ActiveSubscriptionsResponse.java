package com.att.eg.profile.mysubscriptions.info.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveSubscriptionsResponse {
	private Header header;
	private SubscriptionData data;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public SubscriptionData getData() {
		return data;
	}

	public void setData(SubscriptionData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ActiveSubscriptionsResponse{" +
			"header=" + header +
			", data=" + data +
			'}';
	}
}
