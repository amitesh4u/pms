/**
 * 
 */
package com.amitesh.pms.transformer;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;

import com.amitesh.pms.domainobject.ReservationDo;
import com.amitesh.pms.entity.Reservation;
import com.amitesh.pms.util.DateUtil;
import com.amitesh.pms.util.ReservationStatusEnm;

/**
 * @author Amitesh
 *
 */
public class ReservationDoTransformer {

	private ReservationDoTransformer() {
	}

	public static ReservationDo transform(final Reservation reservation) {
		if (ObjectUtils.isEmpty(reservation)) {
			return null;
		}
		ReservationDo reservationDo = new ReservationDo();
		reservationDo.setProfileId(reservation.getProfileId());
		reservationDo.setReservationNo(reservation.getReservationNo());
		reservationDo.setRezStatus(reservation.getRezStatus());
		reservationDo.setRoomNo(reservation.getRoomNo());
		reservationDo.setCheckedInAt(DateUtil.getTimeInMillisec(reservation.getCheckedInAt()));
		reservationDo.setCheckedOutAt(DateUtil.getTimeInMillisec(reservation.getCheckedOutAt()));
		
		updateStatusForAutoCheckout(reservationDo);
		
		return reservationDo;
	}

	private static void updateStatusForAutoCheckout(ReservationDo reservationDo) {
		if (ReservationStatusEnm.INHOUSE.getCode().equalsIgnoreCase(reservationDo.getRezStatus())
				&& reservationDo.getCheckedOutAt() <= System.currentTimeMillis()) {
			reservationDo.setRezStatus(ReservationStatusEnm.CHECKEDOUT.getCode());
		}
	}

	public static List<ReservationDo> transform(List<Reservation> reservations) {
		return reservations.stream().map(ReservationDoTransformer::transform).filter(o -> !ObjectUtils.isEmpty(o))
				.collect(Collectors.toList());
	}

}
