/**
 * 
 */
package com.amitesh.pms.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

/**
 * @author SG0300747
 *
 */
public class JsonUtil {
	private static final Logger LOGGER = LogManager.getLogger(JsonUtil.class);
	
	private JsonUtil(){}

	public static Object convertToObject(final String requestString, final Class<?> clazz) {
		if(StringUtils.isEmpty(requestString)) {
			return null;
		}
		try{
			return new Gson().fromJson(requestString, clazz);
		}catch(Exception e) {
			LOGGER.error("Invalid Request" + requestString, e);
			return null;
		}
	}

}
