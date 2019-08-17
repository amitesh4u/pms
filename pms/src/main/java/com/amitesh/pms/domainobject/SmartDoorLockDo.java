/**
 * 
 */
package com.amitesh.pms.domainobject;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Amitesh
 *
 */
public class SmartDoorLockDo implements PmsDomainObject {

	private static final long serialVersionUID = -3175676539713797526L;

	private String reservationNo;

	private String roomNo;

	private String reqId;

	private long reqTime;

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("reservationNo", reservationNo).append("roomNo", roomNo)
				.append("reqId", reqId).append("reqTime", reqTime).toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SmartDoorLockDo)) {
			return false;
		}
		SmartDoorLockDo castOther = (SmartDoorLockDo) other;
		return new EqualsBuilder().append(reservationNo, castOther.reservationNo).append(roomNo, castOther.roomNo)
				.append(reqId, castOther.reqId).append(reqTime, castOther.reqTime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(reservationNo).append(roomNo).append(reqId).append(reqTime).toHashCode();
	}

	public String getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public long getReqTime() {
		return reqTime;
	}

	public void setReqTime(long reqTime) {
		this.reqTime = reqTime;
	}

}
