/**
 * 
 */
package com.amitesh.pms.util;

/**
 * @author SG0300747
 *
 */
public enum UnlockDoorStatusEnum {
	
	INVALID_REQUEST("Invalid Request"),
	WRONG_DOOR("Wrong Door"),
	EXPIRED_CODE("Expired Code. Please Re-generate"),
	NOT_INHOUSE("Not an In-House Guest"),
	ALREADY_CHECKEDOUT("Already Checked-out"),
	SUCCESS("SUCCESS");
	
	private String statusMsg;
	
	UnlockDoorStatusEnum(String statusMsg){
		this.statusMsg = statusMsg;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
}
