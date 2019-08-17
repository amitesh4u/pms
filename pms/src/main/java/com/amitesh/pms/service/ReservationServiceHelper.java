/**
 * 
 */
package com.amitesh.pms.service;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;

import com.amitesh.pms.entity.Reservation;
import com.amitesh.pms.util.DateUtil;
import com.amitesh.pms.util.RandomUtil;
import com.amitesh.pms.util.ReservationStatusEnm;

/**
 * @author Amitesh
 *
 */
public class ReservationServiceHelper {
	
	private static final String REZ_PREFIX = "REZ";
	private static final String ROOM_NO_PREFIX = "RM";
	
	private ReservationServiceHelper (){}

	public static Reservation getNewReservation(final String profileId) {
		Reservation newReservation = new Reservation();
		newReservation.setProfileId(profileId);
		newReservation.setReservationNo(REZ_PREFIX+ RandomUtil.getRandomNumberLong());
		newReservation.setRezStatus(ReservationStatusEnm.ARRIVING.getCode());
		newReservation.setCreatedAt(DateUtil.getCurrentUTCDate());
		return newReservation;
	}
	
	public static Reservation checkInReservation(Reservation reservation) {
		reservation.setRezStatus(ReservationStatusEnm.INHOUSE.getCode());
		reservation.setRoomNo(ROOM_NO_PREFIX + RandomUtil.getRandomNumberInt());

		Date checkInDate = DateUtil.getCurrentUTCDate();
		Date checkOutDate = DateUtil.addDateTime(checkInDate, Calendar.MINUTE, 5);
		reservation.setCheckedInAt(checkInDate);
		reservation.setCheckedOutAt(checkOutDate);
		return reservation;
	}

	public static Reservation checkOutReservation(Reservation reservation) {
		reservation.setRezStatus(ReservationStatusEnm.CHECKEDOUT.getCode());
		reservation.setCheckedOutAt(DateUtil.getCurrentUTCDate());
		return reservation;
	}

	public static String changeRoomNo(final String roomNo) {
		String newRoomNo = null;
		do {
			newRoomNo = ROOM_NO_PREFIX + RandomUtil.getRandomNumberInt();
		} while (roomNo.equalsIgnoreCase(newRoomNo));

		return newRoomNo;
	}

	public static boolean isStillInHouse(final Reservation reservation) {
		return ObjectUtils.isEmpty(reservation.getCheckedOutAt()) || reservation.getCheckedOutAt().getTime() > System.currentTimeMillis();
	}
}
