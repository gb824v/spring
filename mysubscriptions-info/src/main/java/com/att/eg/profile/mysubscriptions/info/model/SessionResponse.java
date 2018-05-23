package com.att.eg.profile.mysubscriptions.info.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionResponse {
	private Header header;
	private SessionResponseData data;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public SessionResponseData getData() {
		return data;
	}

	public void setData(SessionResponseData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SessionResponse{" +
				"header=" + header +
				", data=" + data +
				'}';
	}
}
