package com.wizard.util.file.json;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public final class JsonUtil {

	private JsonUtil() {
		throw new UnsupportedOperationException("Not supported");
	}

	public static String convertToJson(final Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			return null;
		}
	}

	public static <X> X convertToObject(final String str, final Class<X> cls) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(str, cls);
		} catch (IOException e) {
			return null;
		}
	}

	public static Map<?, ?> convertToMap(final String str) {
		return convertToObject(str, Map.class);
	}

}
