/**
 * 
 */
package com.amitesh.pms.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amitesh.pms.entity.SmartDoorLockHistory;

/**
 * @author Amitesh
 *
 */
@Repository
public interface SmartDoorLockHistoryRepository extends JpaRepository<SmartDoorLockHistory, UUID> {
	
	List<SmartDoorLockHistory> findTop100ByDoorLockNoOrderByReqTimeDesc(String doorLockNo);
	
	List<SmartDoorLockHistory> findTop100ByReservationNoOrderByReqTimeDesc(String rezNo);

}
