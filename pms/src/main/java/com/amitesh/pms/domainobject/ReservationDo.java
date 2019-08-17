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
public class ReservationDo implements PmsDomainObject {
	
	private static final long serialVersionUID = 431346529697615865L;

	private String profileId;
	
	private String reservationNo;
	
	private String rezStatus;
	
	private String roomNo;
	
	private Long checkedInAt;
	
	private Long checkedOutAt;

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("profileId", profileId).append("reservationNo", reservationNo)
				.append("rezStatus", rezStatus).append("roomNo", roomNo).append("checkedInAt", checkedInAt)
				.append("checkedOutAt", checkedOutAt).toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ReservationDo)) {
			return false;
		}
		ReservationDo castOther = (ReservationDo) other;
		return new EqualsBuilder().append(profileId, castOther.profileId).append(reservationNo, castOther.reservationNo)
				.append(rezStatus, castOther.rezStatus).append(roomNo, castOther.roomNo)
				.append(checkedInAt, castOther.checkedInAt).append(checkedOutAt, castOther.checkedOutAt).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(profileId).append(reservationNo).append(rezStatus).append(roomNo)
				.append(checkedInAt).append(checkedOutAt).toHashCode();
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}

	public String getRezStatus() {
		return rezStatus;
	}

	public void setRezStatus(String rezStatus) {
		this.rezStatus = rezStatus;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public Long getCheckedInAt() {
		return checkedInAt;
	}

	public void setCheckedInAt(Long checkedInAt) {
		this.checkedInAt = checkedInAt;
	}

	public Long getCheckedOutAt() {
		return checkedOutAt;
	}

	public void setCheckedOutAt(Long checkedOutAt) {
		this.checkedOutAt = checkedOutAt;
	}
	
}
