package com.att.eg.profile.mysubscriptions.info.model;

public class ColorCode {
	private String startCode;
	private String endCode;

	public ColorCode() {
		//needed for serialization		
	}
	
	public ColorCode(String startCode, String endCode) {
		this.startCode = startCode;
		this.endCode = endCode;
	}

	public String getStartCode() {
		return startCode;
	}

	public void setStartCode(String startCode) {
		this.startCode = startCode;
	}

	public String getEndCode() {
		return endCode;
	}

	public void setEndCode(String endCode) {
		this.endCode = endCode;
	}
}
