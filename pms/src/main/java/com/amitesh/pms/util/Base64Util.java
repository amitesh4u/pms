/**
 * 
 */
package com.amitesh.pms.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author SG0300747
 *
 */
public class Base64Util {
	
	private static final Logger LOGGER = LogManager.getLogger(Base64Util.class);
	
	private Base64Util() {}

	private static final Base64.Encoder MIME_ENCODER = Base64.getMimeEncoder();
	private static final Base64.Decoder MIME_DECODER = Base64.getMimeDecoder();

	public static String decodeString(final String requestString) {
		try{
			LOGGER.debug("Encoded Request: {}", requestString);
			byte[] mimeDecoded = MIME_DECODER.decode(requestString);
	        String mimeDecodedString = new String(mimeDecoded, StandardCharsets.UTF_8);
	        LOGGER.debug("Decoded Request: {}", mimeDecodedString);
			return mimeDecodedString;
		}catch(Exception e) {
			LOGGER.error("Invalid Request" + requestString, e);
			return null;
		}
	}
		

	public static String encodeString(final String requestString) {
		LOGGER.debug("Request: {}", requestString);
        String mimeEncodedString = MIME_ENCODER.encodeToString(requestString.getBytes());
        LOGGER.debug("Encoded Request: {}", mimeEncodedString);
		return mimeEncodedString;
	}

}
