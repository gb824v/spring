package com.att.eg.profile.mysubscriptions.info.model;

public class AddOn {
	private String displayName;
	private int channelId;
	private String priceUsd;
	private boolean isActive;
	
	public AddOn() {
		// needed for deserializer
	}
	
	public AddOn(String displayName, int channelId) {
		this.displayName = displayName;
		this.channelId=channelId;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName=displayName;
	}
	
	public void setChannelId(int channelId) {
		this.channelId=channelId;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public int getChannelId() {
		return channelId;
	}

	public String getPriceUsd() {
		return priceUsd;
	}

	public void setPriceUsd(String priceUsd) {
		this.priceUsd = priceUsd;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
