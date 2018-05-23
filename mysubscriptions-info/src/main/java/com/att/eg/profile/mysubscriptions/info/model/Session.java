package com.att.eg.profile.mysubscriptions.info.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {

	private String ovat;
	private String travellingSession;
	private String expiryDate;
	private String id;

	public String getOvat() {
		return ovat;
	}

	public void setOvat(String ovat) {
		this.ovat = ovat;
	}

	public String getTravellingSession() {
		return travellingSession;
	}

	public void setTravellingSession(String travellingSession) {
		this.travellingSession = travellingSession;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Session{" +
				"ovat='" + ovat + '\'' +
				", travellingSession='" + travellingSession + '\'' +
				", expiryDate='" + expiryDate + '\'' +
				", id='" + id ;
	}

}
