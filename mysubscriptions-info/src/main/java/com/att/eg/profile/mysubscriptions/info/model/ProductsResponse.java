package com.att.eg.profile.mysubscriptions.info.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsResponse {
	private Header header;
	private List<Product> data;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public List<Product> getData() {
		return data;
	}

	public void setData(List<Product> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ProductsResponse{" +
			"header=" + header +
			", data=" + data +
			'}';
	}
}
