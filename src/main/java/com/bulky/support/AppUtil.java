/**
 * 
 */
package com.bulky.support;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;

import com.bulky.action.ActBooking;
import com.bulky.error.DataException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * @author kaala
 *
 */
public final class AppUtil {
	
	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd'T'HH:mm");
	public static SimpleDateFormat DATE_FORMAT_ITA = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat DATE_TIME_FORMAT_ITA = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public static SimpleDateFormat DATE_TIME2_FORMAT_ITA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static SimpleDateFormat[] DATE_FORMATS = {DATE_FORMAT,DATE_TIME2_FORMAT_ITA,DATE_TIME_FORMAT_ITA,DATE_FORMAT_ITA};

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
	
	public static <T> List<T> bindToList(String payload, Class<T> valueType) throws DataException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().configure(Feature.ESCAPE_NON_ASCII, true);
			mapper.getFactory().configure(Feature.IGNORE_UNKNOWN, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(payload,  new TypeReference<List<T>>() { });
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

	public static String formatDateAsText(Date start, Date end, Locale locale) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM",locale);
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		SimpleDateFormat hour = new SimpleDateFormat("HH:mm");
		StringBuffer sb = new StringBuffer();
		sb.append(year.format(end));
		sb.append(" ");
		sb.append(sdf.format(start));		
		sb.append(" ");
		sb.append(hour.format(start));
		sb.append(" -- ");
		sb.append(hour.format(end));		
		return sb.toString();
	}

	public static String getStringValueOf(JSONObject plObj, String key) {
		if (plObj!=null && plObj.has(key)) {
			return plObj.getString(key);
		}
		return null;
	}

	public static synchronized Map<String, Object> toMap(String[] keys, Object[] o) {
		Map<String, Object> result = new HashMap<>();
		for (int i = 0; i < keys.length; i++) {
			String k = keys[i];
			Object value = o[i];
			if (value==null) {
				
			}else if (value instanceof Date) {
				Date v = (Date) value;
				value = toSzDate(v);				
			}else if (value instanceof java.sql.Date) {
				java.sql.Date v = (java.sql.Date) value;
				if (v!=null) {
					value = toSzDate(new Date(v.getTime()));
				}
			} else if (value instanceof java.sql.Timestamp) {
				java.sql.Timestamp v = (java.sql.Timestamp) value;
				if (v!=null) {
					value = toSzDate(new Date(v.getTime()));
				}
			} 
			result.put(k, value);
		}
		return result;
	}

	public static String toSzDate(Date v) {
		if (v!=null) {
			return DATE_FORMAT.format(v);
		}
		return null;
	}

	public static Date getDateValueOf(JSONObject plo, String key) {
		if (!plo.has(key)) {
			return null;
		}
		String szVal = plo.getString(key);
		if (AppUtil.isEmpty(szVal)) {
			return null;
		}
		for(SimpleDateFormat sdf:DATE_FORMATS) {
			try {
				return sdf.parse(szVal);
			} catch (Exception e) {
			}
		}
		return null;
		
	}
	
	public static StartLimit startLimit(JSONObject plo) {
		Integer _limit = getIntegerValueOf(plo, "limit");
		Integer _start = getIntegerValueOf(plo, "start");
		int limit = _limit == null ?1000:_limit.intValue();
		int start = _start == null ?0:_start.intValue();
		
		return new StartLimit(start,limit);
		
	}

	
}
