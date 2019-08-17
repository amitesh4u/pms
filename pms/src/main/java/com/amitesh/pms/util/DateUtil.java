/**
 * 
 */
package com.amitesh.pms.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.util.ObjectUtils;

/**
 * @author Amitesh
 *
 */
public class DateUtil {
	
	private DateUtil () {}
	
	public static final String UTC = "UTC";

	public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone(UTC);

	public static Date getCurrentUTCDate() {
		Calendar c = Calendar.getInstance(UTC_TIME_ZONE);
		return c.getTime();
	}

	public static Date addDateTime(final Date date, int unit, int value) {
		Calendar c = Calendar.getInstance(UTC_TIME_ZONE);
		c.setTime(date);
		c.add(unit, value);
		return c.getTime();
	}
	
	public static Date getUTCDate(long timeInMilliSec) {
		Calendar c = Calendar.getInstance(UTC_TIME_ZONE);
		c.setTimeInMillis(timeInMilliSec);
		return c.getTime();
	}
	
	public static Long getTimeInMillisec(Date date) {
		if(ObjectUtils.isEmpty(date)) {
			return null;
		}else {
			return date.getTime();
		}
	}
}
