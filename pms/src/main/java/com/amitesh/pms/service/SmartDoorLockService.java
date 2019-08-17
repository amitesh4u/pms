/**
 * 
 */
package com.amitesh.pms.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.amitesh.pms.domainobject.ReservationDo;
import com.amitesh.pms.domainobject.SmartDoorLockDo;
import com.amitesh.pms.domainobject.UnlockDoorResponseDo;
import com.amitesh.pms.entity.SmartDoorLockHistory;
import com.amitesh.pms.repository.SmartDoorLockHistoryRepository;
import com.amitesh.pms.transformer.SmartDoorLockHistoryTransformer;
import com.amitesh.pms.util.Base64Util;
import com.amitesh.pms.util.JsonUtil;
import com.amitesh.pms.util.ReservationStatusEnm;
import com.amitesh.pms.util.UnlockDoorStatusEnum;

/**
 * @author Amitesh
 *
 */
@Service
public class SmartDoorLockService {

	private static final Logger LOGGER = LogManager.getLogger(SmartDoorLockService.class);

	private static final long REQUEST_EXPIRY_THRESHOLD_MILLISEC = 60000; // One Minute

	private SmartDoorLockHistoryRepository smartDoorLockHistoryRepository;
	private ReservationService reservationService;

	@Autowired
	public SmartDoorLockService(SmartDoorLockHistoryRepository smartDoorLockHistoryRepository,
			ReservationService reservationService) {
		this.smartDoorLockHistoryRepository = smartDoorLockHistoryRepository;
		this.reservationService = reservationService;
	}

	public UnlockDoorResponseDo unlockDoor(final String doorLockNo, final String requestString) {
		String msg = null;

		Object unlockDoorRequestObj = JsonUtil.convertToObject(Base64Util.decodeString(requestString), SmartDoorLockDo.class);
		
		if(ObjectUtils.isEmpty(unlockDoorRequestObj)) {
			msg = UnlockDoorStatusEnum.INVALID_REQUEST.getStatusMsg();
		}else {
			SmartDoorLockDo unlockDoorRequest = (SmartDoorLockDo) unlockDoorRequestObj;
			LOGGER.debug("Request {} ", unlockDoorRequest);
			if(!doorLockNo.equalsIgnoreCase(unlockDoorRequest.getRoomNo())) {
				msg = UnlockDoorStatusEnum.WRONG_DOOR.getStatusMsg();
			}else if(System.currentTimeMillis() - unlockDoorRequest.getReqTime() > REQUEST_EXPIRY_THRESHOLD_MILLISEC ) {
				msg = UnlockDoorStatusEnum.EXPIRED_CODE.getStatusMsg();
			}else {
				ReservationDo inhouseReservation = reservationService
						.fetchReservationByStatus(unlockDoorRequest.getReservationNo(), ReservationStatusEnm.INHOUSE.getCode());
				if (ObjectUtils.isEmpty(inhouseReservation)) {
					msg = UnlockDoorStatusEnum.NOT_INHOUSE.getStatusMsg();
				}
				/* Check if user is not auto checked out */
				else if(inhouseReservation.getCheckedOutAt() <= System.currentTimeMillis()) {
					msg = UnlockDoorStatusEnum.ALREADY_CHECKEDOUT.getStatusMsg();
				}else {
					saveSmartDoorLockRequest(doorLockNo, requestString, unlockDoorRequest);
					msg = UnlockDoorStatusEnum.SUCCESS.getStatusMsg();
				}
			}
		}
		
		UnlockDoorResponseDo unlockDoorResponseDo = new UnlockDoorResponseDo();
		unlockDoorResponseDo.setResponse(msg);
		LOGGER.debug("Response {} ", unlockDoorResponseDo);
		return unlockDoorResponseDo;
	}

	private void saveSmartDoorLockRequest(final String doorLockNo, final String requestString,
			SmartDoorLockDo unlockDoorRequest) {
		SmartDoorLockHistory smartDoorLockHistory = SmartDoorLockHistoryTransformer
				.getSmartDoorLockHistory(doorLockNo, unlockDoorRequest);
		try {
			smartDoorLockHistoryRepository.save(smartDoorLockHistory);
		} catch (Exception e) {
			LOGGER.error("ERROR-SAVING-UNLOCK-DOOR-REQ" + requestString, e);
		}
	}

	public List<SmartDoorLockDo> fetchSmartDoorLockHistory(final String doorLockNo) {
		return SmartDoorLockHistoryTransformer.getSmartDoorLockList(
				smartDoorLockHistoryRepository.findTop100ByDoorLockNoOrderByReqTimeDesc(doorLockNo));
	}


	public List<SmartDoorLockDo> fetchSmartKeyHistory(final String rezNo) {
		return SmartDoorLockHistoryTransformer.getSmartDoorLockList(
				smartDoorLockHistoryRepository.findTop100ByReservationNoOrderByReqTimeDesc(rezNo));
	}
}
