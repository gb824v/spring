package com.att.eg.profile.mysubscriptions.info.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription {
    private String sku;
    private String startDate;
    private boolean isBasePackage;
    private String name;
    private String status;
    private boolean isFreeTrial;
    private String retailPrice;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@JsonProperty(value="isBasePackage")
	public boolean isBasePackage() {
		return isBasePackage;
	}

	public void setBasePackage(boolean basePackage) {
		isBasePackage = basePackage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty(value="isFreeTrial")
	public boolean isFreeTrial() {
		return isFreeTrial;
	}

	public void setFreeTrial(boolean freeTrial) {
		isFreeTrial = freeTrial;
	}

	public String getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}

	@Override
	public String toString() {
		return "Subscription{" +
			"sku='" + sku + '\'' +
			", startDate='" + startDate + '\'' +
			", isBasePackage=" + isBasePackage +
			", name='" + name + '\'' +
			", status='" + status + '\'' +
			", isFreeTrial=" + isFreeTrial +
			", retailPrice='" + retailPrice + '\'' +
			'}';
	}
}
