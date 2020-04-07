/**
 * 
 */
package com.amitesh.pms.controller;

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
import com.amitesh.pms.domainobject.SmartDoorLockDo;
import com.amitesh.pms.service.ReservationService;
import com.amitesh.pms.service.SmartDoorLockService;

/**
 * @author Amitesh
 *
 */

@RestController
@RequestMapping("/service/v1/reservation/{reservationNo}")
public class ReservationController implements IController {

	private static final String REQUEST_RECEIVED_AT_FOR_RESERVATION_NO = "Request Received at {} for reservationNo {}";

	private static final Logger LOGGER = LogManager.getLogger(ReservationController.class);
	
	private ReservationService reservationService;
	private SmartDoorLockService smartDoorLockService;
	
	@Autowired
	public ReservationController(ReservationService reservationService, SmartDoorLockService smartDoorLockService) {
		this.reservationService = reservationService;
		this.smartDoorLockService = smartDoorLockService;
	}

	@GetMapping(value="/checkin", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ReservationDo> checkInReservation(@PathVariable String reservationNo){
		LOGGER.debug(REQUEST_RECEIVED_AT_FOR_RESERVATION_NO, System.currentTimeMillis(), reservationNo);
		ReservationDo reservation = reservationService.checkInReservation(reservationNo);
		if(!ObjectUtils.isEmpty(reservation)) {
			return ResponseEntity.ok().body(reservation);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping(value="/changeroom", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ReservationDo> changeRoomForReservation(@PathVariable String reservationNo){
		LOGGER.debug(REQUEST_RECEIVED_AT_FOR_RESERVATION_NO, System.currentTimeMillis(), reservationNo);
		ReservationDo reservation = reservationService.changeRoomNo(reservationNo);
		if(!ObjectUtils.isEmpty(reservation)) {
			return ResponseEntity.ok().body(reservation);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping(value="/checkout", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ReservationDo> checkOutReservation(@PathVariable String reservationNo){
		LOGGER.debug(REQUEST_RECEIVED_AT_FOR_RESERVATION_NO, System.currentTimeMillis(), reservationNo);
		ReservationDo reservation = reservationService.checkOutReservation(reservationNo);
		if(!ObjectUtils.isEmpty(reservation)) {
			return ResponseEntity.ok().body(reservation);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping(value = "/smartkeyusage", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SmartDoorLockDo>> getSmartKeyUsage(@PathVariable String reservationNo) {
		LOGGER.debug(REQUEST_RECEIVED_AT_FOR_RESERVATION_NO, System.currentTimeMillis(), reservationNo);
		return ResponseEntity.ok().body(smartDoorLockService.fetchSmartKeyHistory(reservationNo));
	}
}
