package com.att.eg.profile.mysubscriptions.info.model;

public class AddOnsInfo {
	private String addOnsCount;
	private String totalPriceUsd;
	private AddOn[] addOns;
	
	public AddOnsInfo() {
		// needed for deserializer
	}
	
	public AddOnsInfo(String addOnsCount, String totalPriceUsd, AddOn[] addOns) {
		this.addOnsCount = addOnsCount;
		this.totalPriceUsd = totalPriceUsd;
		this.addOns = addOns;
	}
	
	public String getAddOnsCount() {
		return addOnsCount;
	}
	public void setAddOnsCount(String addOnsCount) {
		this.addOnsCount = addOnsCount;
	}
	public String getTotalPriceUsd() {
		return totalPriceUsd;
	}
	public void setTotalPriceUsd(String totalPriceUsd) {
		this.totalPriceUsd = totalPriceUsd;
	}
	public AddOn[] getAddOns() {
		return addOns;
	}
	public void setAddOns(AddOn[] addOns) {
		this.addOns = addOns;
	}
}
