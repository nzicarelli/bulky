/**
 * 
 */
package com.bulky.support;

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
}
