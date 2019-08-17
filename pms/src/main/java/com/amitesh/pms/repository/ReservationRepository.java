/**
 * 
 */
package com.amitesh.pms.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amitesh.pms.entity.Reservation;

/**
 * @author Amitesh
 *
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID>{
	
	List<Reservation> findByProfileIdAndRezStatusInOrderByCreatedAtDesc(String profileId, List<String> rezStatuses);
	
	List<Reservation> findByReservationNoAndRezStatusInOrderByCreatedAtDesc(String reservationNo, List<String> rezStatuses);

	List<Reservation> findByProfileIdOrderByCreatedAtDesc(String profileId);
	
}
