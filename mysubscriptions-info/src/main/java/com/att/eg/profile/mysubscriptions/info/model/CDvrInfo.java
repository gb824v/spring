package com.att.eg.profile.mysubscriptions.info.model;

public class CDvrInfo {
	private String availableHours;
	private String priceUsd;
	
	public CDvrInfo(){
		// needed for deserializer
	}
	
	public CDvrInfo(String availableHours, String priceUsd) {
		this.availableHours = availableHours;
		this.priceUsd = priceUsd;
	}
	
	public void setAvailableHours(String availableHours) {
		this.availableHours=availableHours;
	}
	
	public void setPriceUsd(String priceUsd) {
		this.priceUsd=priceUsd;
	}
	
	public String getAvailableHours() {
		return availableHours;
	}
	
	public String getPriceUsd() {
		return priceUsd;
	}
}
