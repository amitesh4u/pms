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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Amitesh
 *
 */
@Entity
@Table(name = "RESERVATION")
public class Reservation implements PmsEntity {
	
	private static final long serialVersionUID = -3713612759228785101L;
	
	@Id
	@Column(name = "REZ_GUID", columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "com.amitesh.pms.entity.FallbackUUIDGenerator")
	private UUID id;

	private String profileId;
	
	@Column(name = "REZ_NO")
	private String reservationNo;
	
	private String rezStatus;
	
	private String roomNo;
	
	private Date checkedInAt;
	
	private Date checkedOutAt;
	
	private Date createdAt;

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("profileId", profileId)
				.append("reservationNo", reservationNo).append("rezStatus", rezStatus).append("roomNo", roomNo)
				.append("checkedInAt", checkedInAt).append("checkedOutAt", checkedOutAt).append("createdAt", createdAt)
				.toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Reservation)) {
			return false;
		}
		Reservation castOther = (Reservation) other;
		return new EqualsBuilder().append(id, castOther.id).append(profileId, castOther.profileId)
				.append(reservationNo, castOther.reservationNo).append(rezStatus, castOther.rezStatus)
				.append(roomNo, castOther.roomNo).append(checkedInAt, castOther.checkedInAt)
				.append(checkedOutAt, castOther.checkedOutAt).append(createdAt, castOther.createdAt).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(profileId).append(reservationNo).append(rezStatus).append(roomNo)
				.append(checkedInAt).append(checkedOutAt).append(createdAt).toHashCode();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public Date getCheckedInAt() {
		return checkedInAt;
	}

	public void setCheckedInAt(Date checkedInAt) {
		this.checkedInAt = checkedInAt;
	}

	public Date getCheckedOutAt() {
		return checkedOutAt;
	}

	public void setCheckedOutAt(Date checkedOutAt) {
		this.checkedOutAt = checkedOutAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
