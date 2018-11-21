/**
 * 
 */
package com.bulky.support;

import org.json.JSONObject;

import com.bulky.error.DataException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * @author kaala
 *
 */
public final class AppUtil {

	public static boolean isEmpty(String value) {
		return value == null || value.trim().length()==0;
	}

	public static <T> T  bindObject(String payload, Class<T> valueType) throws DataException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().configure(Feature.ESCAPE_NON_ASCII, true);
			mapper.getFactory().configure(Feature.IGNORE_UNKNOWN, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(payload, valueType);
		} catch (Exception e) {
			throw new DataException(e);
		}	
	}
	
	public static String toJson(Object o) throws DataException {		
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().configure(Feature.ESCAPE_NON_ASCII, true);
			return mapper.writeValueAsString(o);
		} catch (Exception e) {
			throw new DataException(e);
		}
		
	}

	public static JSONObject toPayLoad(String payload) {
		try {
			return new JSONObject(payload);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}

	public static Integer getIntegerValueOf(JSONObject payload, String key) {
		if (payload!=null && payload.has(key)) {
			try {
				return Integer.valueOf(payload.getInt(key));
			} catch (Exception e) {
				try {
					return Integer.valueOf(payload.getString(key));
				} catch (Exception e2) {
				}
			}
		}
		return null;
	}

	public static boolean isEmpty(Integer id) {
		return id==null || id.intValue()==0;		
	}
}
