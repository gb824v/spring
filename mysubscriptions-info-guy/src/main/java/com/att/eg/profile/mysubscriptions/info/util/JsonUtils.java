package com.att.eg.profile.mysubscriptions.info.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nb977h on 3/14/2017
 */
@Component
public class JsonUtils {

	private static ObjectMapper mapper;

	public JsonUtils() {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
	}

	public Map<String, Object> getMapFromJsonString(String jsonStr) throws IOException {
		return mapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {
		});
	}

	public <T> List<T> getListFromString(String jsonString, Class clazz) throws IOException {
		JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);

		// noinspection unchecked
		return mapper.readValue(jsonString, type);
	}




	public <T> T getObjectFromString(String jsonString, Class<T> valueType) throws IOException {
		return mapper.readValue(jsonString, valueType);
	}


	public String createStringFromObjectList(List<Object> list) throws JsonProcessingException {
		return mapper.writeValueAsString(list);
	}


	public String getObjectAsString(Object object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}
}
