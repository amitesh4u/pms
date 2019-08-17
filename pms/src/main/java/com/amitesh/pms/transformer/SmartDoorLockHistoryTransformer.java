/**
 * 
 */
package com.amitesh.pms.transformer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.amitesh.pms.domainobject.SmartDoorLockDo;
import com.amitesh.pms.entity.SmartDoorLockHistory;
import com.amitesh.pms.util.DateUtil;

/**
 * @author Amitesh
 *
 */
public class SmartDoorLockHistoryTransformer {

	private SmartDoorLockHistoryTransformer() {
	}

	public static SmartDoorLockHistory getSmartDoorLockHistory(final String doorLockNo, final SmartDoorLockDo smartDoorLockDo) {
		SmartDoorLockHistory smartDoorLockHistory = new SmartDoorLockHistory();
		smartDoorLockHistory.setDoorLockNo(doorLockNo);
		smartDoorLockHistory.setReservationNo(smartDoorLockDo.getReservationNo());
		smartDoorLockHistory.setRoomNo(smartDoorLockDo.getRoomNo());
		smartDoorLockHistory.setReqId(smartDoorLockDo.getReqId());
		smartDoorLockHistory.setReqTime(DateUtil.getUTCDate(smartDoorLockDo.getReqTime()));
		return smartDoorLockHistory;
	}

	public static List<SmartDoorLockDo> getSmartDoorLockList(
			final List<SmartDoorLockHistory> smartDoorLockHistoryList) {
		if(CollectionUtils.isEmpty(smartDoorLockHistoryList)) {
			return Collections.emptyList();
		}
		return smartDoorLockHistoryList.stream().map(SmartDoorLockHistoryTransformer::getSmartDoorLock)
				.collect(Collectors.toList());
	}

	public static SmartDoorLockDo getSmartDoorLock(final SmartDoorLockHistory smartDoorLockHistory) {
		SmartDoorLockDo smartDoorLockDo = new SmartDoorLockDo();
		smartDoorLockDo.setReservationNo(smartDoorLockHistory.getReservationNo());
		smartDoorLockDo.setRoomNo(smartDoorLockHistory.getRoomNo());
		smartDoorLockDo.setReqId(smartDoorLockHistory.getReqId());
		smartDoorLockDo.setReqTime(smartDoorLockHistory.getReqTime().getTime());
		return smartDoorLockDo;
	}
}
