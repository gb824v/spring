package com.att.eg.profile.mysubscriptions.info.model;

public class MySubscriptionsInfoResponse {
	private BasePackageInfo basePackageInfo;
	private AddOnsInfo addOnsInfo;
	private CDvrInfo cDvrInfo;
	private String nextBillingAmount;

	public MySubscriptionsInfoResponse() {
		// needed for deserializer

	}
	
	public MySubscriptionsInfoResponse(BasePackageInfo basePackageInfo, AddOnsInfo addOnsInfo, CDvrInfo cDvrInfo, String nextBillingAmount) {
		this.basePackageInfo = basePackageInfo;
		this.addOnsInfo = addOnsInfo;
		this.cDvrInfo = cDvrInfo;
		this.nextBillingAmount = nextBillingAmount;
	}
	
	public BasePackageInfo getBasePackageInfo() {
		return basePackageInfo;
	}

	public AddOnsInfo getAddOnsInfo() {
		return addOnsInfo;
	}
	
	public CDvrInfo getCDvrInfo() {
		return cDvrInfo;
	}
	
	public void setBasePackageInfo(BasePackageInfo basePackageInfo) {
		this.basePackageInfo = basePackageInfo;
	}
	
	public void setAddOnsInfo(AddOnsInfo addOnsInfo) {
		this.addOnsInfo = addOnsInfo;
	}
	
	public void setCDvrInfo(CDvrInfo cDvrInfo) {
		this.cDvrInfo = cDvrInfo;
	}

	public String getNextBillingAmount() {
		return nextBillingAmount;
	}

	public void setNextBillingAmount(String nextBillingAmount) {
		this.nextBillingAmount = nextBillingAmount;
	}	
}
