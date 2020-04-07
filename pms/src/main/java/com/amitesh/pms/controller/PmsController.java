/**
 * 
 */
package com.amitesh.pms.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SG0300747
 *
 */
@RestController
public class PmsController implements IController {
	
	private static final String REQUEST_RECEIVED_AT = "Request Received at {} ";
	private static final Logger LOGGER = LogManager.getLogger(PmsController.class);

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> helloWorld() {
		LOGGER.debug(REQUEST_RECEIVED_AT, System.currentTimeMillis());
		LOGGER.debug("Response sent at {}", System.currentTimeMillis());
		return new ResponseEntity<>("Hello World!!", HttpStatus.OK);
	}
	
	@GetMapping(value = "/ping", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> ping() {
		LOGGER.debug(REQUEST_RECEIVED_AT, System.currentTimeMillis());
		LOGGER.debug("Response sent pong at {}", System.currentTimeMillis());
		return new ResponseEntity<>("pong", HttpStatus.OK);
	}
}
