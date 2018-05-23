package com.att.eg.profile.mysubscriptions.info.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private String sku;
    private String productName;
    private String displayName;
    private String displayOrder;
    private String description;
    private boolean renewable;
    private String frequency;
    private boolean basicService;
    private String price;
    private String currencyCode;
    private String serviceType;
    private SCOfferType[] offerTypes;
    private int concurrentPlayCount;
    private int storageCapacity; // only seen where Category is DVR
    private int dataRetentionPeriod; // only seen where Category is DVR
    private boolean adFastForward;
    private String category;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRenewable() {
		return renewable;
	}

	public void setRenewable(boolean renewable) {
		this.renewable = renewable;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public boolean isBasicService() {
		return basicService;
	}

	public void setBasicService(boolean basicService) {
		this.basicService = basicService;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public SCOfferType[] getOfferTypes() {
		return offerTypes;
	}

	public void setOfferTypes(SCOfferType[] offerTypes) {
		this.offerTypes = offerTypes;
	}

	public int getConcurrentPlayCount() {
		return concurrentPlayCount;
	}

	public void setConcurrentPlayCount(int concurrentPlayCount) {
		this.concurrentPlayCount = concurrentPlayCount;
	}

	public int getStorageCapacity() {
		return storageCapacity;
	}

	public void setStorageCapacity(int storageCapacity) {
		this.storageCapacity = storageCapacity;
	}

	public int getDataRetentionPeriod() {
		return dataRetentionPeriod;
	}

	public void setDataRetentionPeriod(int dataRetentionPeriod) {
		this.dataRetentionPeriod = dataRetentionPeriod;
	}

	public boolean isAdFastForward() {
		return adFastForward;
	}

	public void setAdFastForward(boolean adFastForward) {
		this.adFastForward = adFastForward;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product{" +
			"sku='" + sku + '\'' +
			", productName='" + productName + '\'' +
			", displayName='" + displayName + '\'' +
			", displayOrder='" + displayOrder + '\'' +
			", description='" + description + '\'' +
			", renewable=" + renewable +
			", frequency='" + frequency + '\'' +
			", basicService=" + basicService +
			", price='" + price + '\'' +
			", currencyCode='" + currencyCode + '\'' +
			", serviceType='" + serviceType + '\'' +
			", offerTypes=" + Arrays.toString(offerTypes) +
			", concurrentPlayCount=" + concurrentPlayCount +
			", storageCapacity=" + storageCapacity +
			", dataRetentionPeriod" + dataRetentionPeriod +
			", adFastForward=" + adFastForward +
			", category='" + category + '\'' +
			'}';
	}
}