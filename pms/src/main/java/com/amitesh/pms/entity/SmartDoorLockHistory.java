/**
 * 
 */
package com.amitesh.pms.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Amitesh
 *
 */
@Entity
@Table(name = "SMART_DOOR_LOCK_HIST")
public class SmartDoorLockHistory implements PmsEntity {
	
	private static final long serialVersionUID = 4466351208664683873L;

	@Id
	@Column(name = "HIST_GUID", columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "com.amitesh.pms.entity.FallbackUUIDGenerator")
	private UUID id;
	
	private String doorLockNo;
		
	@Column(name = "REZ_NO")
	private String reservationNo;
	
	private String roomNo;
	
	private String reqId;
	
	private Date reqTime;

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("doorLockNo", doorLockNo)
				.append("reservationNo", reservationNo).append("roomNo", roomNo).append("reqId", reqId)
				.append("reqTime", reqTime).toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SmartDoorLockHistory)) {
			return false;
		}
		SmartDoorLockHistory castOther = (SmartDoorLockHistory) other;
		return new EqualsBuilder().append(id, castOther.id).append(doorLockNo, castOther.doorLockNo)
				.append(reservationNo, castOther.reservationNo).append(roomNo, castOther.roomNo)
				.append(reqId, castOther.reqId).append(reqTime, castOther.reqTime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(doorLockNo).append(reservationNo).append(roomNo).append(reqId)
				.append(reqTime).toHashCode();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDoorLockNo() {
		return doorLockNo;
	}

	public void setDoorLockNo(String doorLockNo) {
		this.doorLockNo = doorLockNo;
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

	public Date getReqTime() {
		return reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}
	
	
}
