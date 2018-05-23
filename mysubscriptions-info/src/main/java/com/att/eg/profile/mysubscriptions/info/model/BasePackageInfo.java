package com.att.eg.profile.mysubscriptions.info.model;

public class BasePackageInfo {
	private String displayName;
	private String channelCount;
	private String priceUsd;
	private ColorCode colorCode;
	
	
	public BasePackageInfo() {
		// needed for deserializer
	}
	
	public BasePackageInfo(String displayName, String channelCount, String priceUsd, ColorCode colorCode) {
		this.displayName = displayName;
		this.channelCount = channelCount;
		this.priceUsd = priceUsd;
		this.colorCode = colorCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getChannelCount() {
		return channelCount;
	}

	public void setChannelCount(String channelCount) {
		this.channelCount = channelCount;
	}

	public String getPriceUsd() {
		return priceUsd;
	}

	public void setPriceUsd(String priceUsd) {
		this.priceUsd = priceUsd;
	}

	public ColorCode getColorCode() {
		return colorCode;
	}

	public void setColorCode(ColorCode colorCode) {
		this.colorCode = colorCode;
	}
}
