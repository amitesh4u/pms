/**
 * 
 */
package com.amitesh.pms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.amitesh.pms.domainobject.ReservationDo;
import com.amitesh.pms.entity.Reservation;
import com.amitesh.pms.repository.ReservationRepository;
import com.amitesh.pms.transformer.ReservationDoTransformer;
import com.amitesh.pms.util.ReservationStatusEnm;

/**
 * @author Amitesh
 *
 */
@Service
public class ReservationService {

	private static final Logger LOGGER = LogManager.getLogger(ReservationService.class);

	private ReservationRepository reservationRepository;

	@Autowired
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public ReservationDo createReservation(final String profileId) {
		ReservationDo reservation = null;
		try {
			Reservation newReservation = ReservationServiceHelper.getNewReservation(profileId);
			LOGGER.debug("New Reservation object {}" , newReservation);
			reservation = ReservationDoTransformer.transform(updateReservationEntity(newReservation));
			LOGGER.debug("Arriving Reservation {} for Profile {}", reservation, profileId);
		} catch (Exception e) {
			LOGGER.error("ERROR-CREATING-REZ-FOR-PROFILE" + profileId, e);
		}
		return reservation;
	}
	

	public List<ReservationDo> fetchAllReservationsForGuest(final String profileId) {
		List<ReservationDo> reservations = new ArrayList<>(0);
		try {
			List<Reservation> inHouseReservations = reservationRepository.findByProfileIdOrderByCreatedAtDesc(profileId);
			/* Check if user is not auto checked out */
			if (!CollectionUtils.isEmpty(inHouseReservations)) {
				reservations = ReservationDoTransformer.transform(inHouseReservations);
				LOGGER.debug("Reservations {} for Profile {}", reservations, profileId);
			}
		} catch (Exception e) {
			LOGGER.error("ERROR-FETCHING-ALL-REZ-FOR-PROFILE" + profileId, e);
		}
		return reservations;
	}
	

	public ReservationDo fetchActiveReservationForGuest(final String profileId) {
		ReservationDo reservation = null;
		try {
			List<Reservation> activeReservations = reservationRepository.findByProfileIdAndRezStatusInOrderByCreatedAtDesc(profileId,
					Arrays.asList(ReservationStatusEnm.ARRIVING.getCode(), ReservationStatusEnm.INHOUSE.getCode()));
			/* Check if user is not auto checked out */
			if (!CollectionUtils.isEmpty(activeReservations)
					&& ReservationServiceHelper.isStillInHouse(activeReservations.get(0))) {
				reservation = ReservationDoTransformer.transform(activeReservations.get(0));
				LOGGER.debug("Active Reservation {} for Profile {}", reservation, profileId);
			}
		} catch (Exception e) {
			LOGGER.error("ERROR-FETCHING-ACTIVE-REZ-FOR-PROFILE" + profileId, e);
		}
		return reservation;
	}

	public ReservationDo fetchReservationByStatus(final String rezNo, final String rezStatus) {
		return ReservationDoTransformer.transform(fetchReservationEntityByStatus(rezNo, Arrays.asList(rezStatus)));
	}

	public ReservationDo changeRoomNo(final String rezNo) {
		Reservation inHouseReservation = fetchReservationEntityByStatus(rezNo,  Arrays.asList(ReservationStatusEnm.INHOUSE.getCode()));
		if (!ObjectUtils.isEmpty(inHouseReservation) && ReservationServiceHelper.isStillInHouse(inHouseReservation)) {
			inHouseReservation.setRoomNo(ReservationServiceHelper.changeRoomNo(inHouseReservation.getRoomNo()));
			inHouseReservation = updateReservationEntity(inHouseReservation);
			LOGGER.debug("Reservation after Room Change {}", inHouseReservation);
			return ReservationDoTransformer.transform(inHouseReservation);
		}
		return null;
	}
	
	public ReservationDo checkInReservation(final String rezNo) {
		Reservation arrivingReservation = fetchReservationEntityByStatus(rezNo,
				 Arrays.asList(ReservationStatusEnm.ARRIVING.getCode()));
		if (!ObjectUtils.isEmpty(arrivingReservation)) {
			arrivingReservation = ReservationServiceHelper.checkInReservation(arrivingReservation);
			arrivingReservation = updateReservationEntity(arrivingReservation);
		}
		LOGGER.debug("Reservation after CheckIn {}", arrivingReservation);
		return ReservationDoTransformer.transform(arrivingReservation);
	}

	public ReservationDo checkOutReservation(final String rezNo) {
		Reservation inHouseReservation = fetchReservationEntityByStatus(rezNo,  Arrays.asList(ReservationStatusEnm.INHOUSE.getCode()));
		if (!ObjectUtils.isEmpty(inHouseReservation) && ReservationServiceHelper.isStillInHouse(inHouseReservation)) {
			inHouseReservation = ReservationServiceHelper.checkOutReservation(inHouseReservation);
			inHouseReservation = updateReservationEntity(inHouseReservation);
		}
		LOGGER.debug("Reservation after CheckOut {}", inHouseReservation);
		return ReservationDoTransformer.transform(inHouseReservation);
	}

	private Reservation updateReservationEntity(final Reservation reservation) {
		LOGGER.debug("Call Update Reservation {} " , reservation);
		try {
			return reservationRepository.save(reservation);
		} catch (Exception e) {
			LOGGER.error("ERROR-UPDATING-REZ-" + reservation, e);
			return null;
		}
	}

	private Reservation fetchReservationEntityByStatus(final String rezNo, final List<String> rezStatuses) {
		Reservation reservation = null;
		try {
			List<Reservation> reservations = reservationRepository.findByReservationNoAndRezStatusInOrderByCreatedAtDesc(rezNo, rezStatuses);
			if (!CollectionUtils.isEmpty(reservations)) {
				reservation = reservations.get(0);
				LOGGER.debug("Reservation {} for Rez No {} and Statuses {} ", reservation, rezNo, rezStatuses);
			}
		} catch (Exception e) {
			LOGGER.error("ERROR-FETCHING-REZ-FOR" + rezNo + "-AND-STATUSES-" + rezStatuses, e);
		}
		return reservation;
	}

}
