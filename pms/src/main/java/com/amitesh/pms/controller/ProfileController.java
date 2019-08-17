/**
 * 
 */
package com.amitesh.pms.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amitesh.pms.domainobject.ReservationDo;
import com.amitesh.pms.service.ReservationService;

/**
 * @author SG0300747
 *
 */
@RestController
@RequestMapping("/service/v1/profile/{profileId}")
public class ProfileController {
	
	private static final String REQUEST_RECEIVED_AT_FOR_PROFILE_ID = "Request Received at {} for profileId {}";
	private static final Logger LOGGER = LogManager.getLogger(ProfileController.class);
	
	public static class Uri {
		private Uri() {}
		
		private static final String RESERVATION = "/reservation";
		private static final String CREATE_RESERVATION = RESERVATION + "/create";
		private static final String FETCH_ACTIVE_RESERVATION = RESERVATION + "/active";
		private static final String FETCH_ALL_RESERVATIONS = RESERVATION + "/all";
	}
	
	private ReservationService reservationService;
	
	@Autowired
	public ProfileController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping(value=Uri.CREATE_RESERVATION, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ReservationDo> createReservation(@PathVariable String profileId){
		LOGGER.debug(REQUEST_RECEIVED_AT_FOR_PROFILE_ID, System.currentTimeMillis(), profileId);
		ReservationDo reservation = reservationService.createReservation(profileId);
		if(!ObjectUtils.isEmpty(reservation)) {
			return ResponseEntity.ok().body(reservation);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping(value=Uri.FETCH_ALL_RESERVATIONS, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ReservationDo>> getReservations(@PathVariable String profileId){
		LOGGER.debug(REQUEST_RECEIVED_AT_FOR_PROFILE_ID, System.currentTimeMillis(), profileId);
		return ResponseEntity.ok().body(reservationService.fetchAllReservationsForGuest(profileId));
	}
	
	@GetMapping(value=Uri.FETCH_ACTIVE_RESERVATION, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ReservationDo>> getActiveReservation(@PathVariable String profileId){
		LOGGER.debug(REQUEST_RECEIVED_AT_FOR_PROFILE_ID, System.currentTimeMillis(), profileId);
		ReservationDo reservation = reservationService.fetchActiveReservationForGuest(profileId);
		List<ReservationDo> response = new ArrayList<>(1);
		if(!ObjectUtils.isEmpty(reservation)) {
			response.add(reservation);
		}
		return ResponseEntity.ok().body(response);
	}
}
