/**
 * 
 */
package com.amitesh.pms.util;

import java.util.Random;

/**
 * @author Amitesh
 *
 */
public class RandomUtil {

	private RandomUtil() {}
	
	private static Random random= new Random();
	
	public static int getRandomNumberInt(){
		return random.ints(100, 1000).findFirst().getAsInt();
	}

	public static long getRandomNumberLong() {
		return random.longs(1000000l,1000000000l).findFirst().getAsLong();
	}
}
