package com.att.eg.profile.mysubscriptions.info.model;

public class SCOfferType {
	private String offerType;
	private String salesChannel;

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	@Override
	public String toString() {
		return "SCOfferType{" +
			"offerType='" + offerType + '\'' +
			", salesChannel='" + salesChannel + '\'' +
			'}';
	}
}
