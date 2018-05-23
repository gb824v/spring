package com.att.eg.profile.mysubscriptions.info.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionData {
	private String nextBillingDate;
	private String nextBillingAmount;
	private List<Subscription> subscriptions;

	public String getNextBillingDate() {
		return nextBillingDate;
	}

	public void setNextBillingDate(String nextBillingDate) {
		this.nextBillingDate = nextBillingDate;
	}

	public String getNextBillingAmount() {
		return nextBillingAmount;
	}

	public void setNextBillingAmount(String nextBillingAmount) {
		this.nextBillingAmount = nextBillingAmount;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	public String toString() {
		return "SubscriptionData{" +
			"nextBillingDate='" + nextBillingDate + '\'' +
			", nextBillingAmount='" + nextBillingAmount + '\'' +
			", subscriptions=" + subscriptions +
			'}';
	}
}
