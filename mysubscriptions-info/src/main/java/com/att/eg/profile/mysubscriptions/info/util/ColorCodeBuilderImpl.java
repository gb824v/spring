package com.att.eg.profile.mysubscriptions.info.util;

import java.util.Locale;

import com.att.eg.profile.mysubscriptions.info.model.ColorCode;

public class ColorCodeBuilderImpl implements ColorCodeBuilder {
	public ColorCodeBuilderImpl() {
		// needed for autowiring
	}
	
	@Override
	public ColorCode buildColorCode(String basePackageName) {
		String startCode;
		String endCode;
		
		if(basePackageName==null || basePackageName.isEmpty()) {
			startCode = "4A4A4A";
			endCode = "4A4A4A"; 
		}
		else {
			switch(basePackageName.toUpperCase(Locale.ENGLISH)) {
				case "LIVE A LITTLE": 
					startCode = "FAC261";
					endCode = "D33221"; 
					break;
				case "JUST RIGHT": 
					startCode = "C4EA80";
					endCode = "429321"; 
					break;
				case "GO BIG": 
					startCode = "5FBDE0";
					endCode = "1B81A7"; 
					break;
				case "GOTTA HAVE IT": 
					startCode = "847DE5";
					endCode = "5C228A";
					break;
				default: 
					startCode = "9F9F9F";
					endCode = "5A5A5A"; 
					break;
			}
		}
		return new ColorCode(startCode, endCode);
	}

}
