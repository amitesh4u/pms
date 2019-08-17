/**
 * 
 */
package com.amitesh.pms.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amitesh.pms.domainobject.SmartDoorLockDo;
import com.amitesh.pms.domainobject.UnlockDoorResponseDo;
import com.amitesh.pms.service.SmartDoorLockService;

/**
 * @author SG0300747
 *
 */
@RestController
@RequestMapping("/service/v1/smartdoorlock/{doorLockNo}")
public class SmartDoorLockController {

	private static final String REQUEST_RECEIVED_AT_FOR_DOOR_LOCK_NO = "Request Received at {} for doorLockNo {}";

	private static final Logger LOGGER = LogManager.getLogger(SmartDoorLockController.class);
	
	private SmartDoorLockService smartDoorLockService;

	@Autowired
	public SmartDoorLockController(SmartDoorLockService smartDoorLockService) {
		this.smartDoorLockService = smartDoorLockService;
	}

	@GetMapping(value = "/unlock", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UnlockDoorResponseDo> unlockDoor(@PathVariable String doorLockNo,
			@RequestParam String request) {
		LOGGER.debug(REQUEST_RECEIVED_AT_FOR_DOOR_LOCK_NO, System.currentTimeMillis(), doorLockNo);
		return ResponseEntity.ok().body(smartDoorLockService.unlockDoor(doorLockNo, request));
	}

	@GetMapping(value = "/usage", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SmartDoorLockDo>> getUsage(@PathVariable String doorLockNo) {
		LOGGER.debug(REQUEST_RECEIVED_AT_FOR_DOOR_LOCK_NO, System.currentTimeMillis(), doorLockNo);
		return ResponseEntity.ok().body(smartDoorLockService.fetchSmartDoorLockHistory(doorLockNo));
	}
}
