/**
 * 
 */
package com.amitesh.pms.util;

/**
 * @author Amitesh
 *
 */
public enum ReservationStatusEnm {
	
	ARRIVING("ARRIVING"),
	INHOUSE("IN-HOUSE"),
	CHECKEDOUT("CHECKED-OUT");
	
	private String code;
	
	ReservationStatusEnm(String code){
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
