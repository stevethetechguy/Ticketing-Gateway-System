package com.synergisticit.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Employee;
import com.synergisticit.domain.Status;
import com.synergisticit.domain.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	List<Ticket> findByStatus(Status status);
	List<Ticket> findByCreatedBy_Id(Long createdById);
	
	 @Query("""
		        SELECT t FROM Ticket t
		        WHERE t.status = :status
		        AND (
		            SELECT MAX(h.actionDate)
		            FROM TicketHistory h
		            WHERE h.ticket = t
		        ) < :cutoffDate
		    """)
		    List<Ticket> findPendingTicketsOlderThan(
		            @Param("status") Status status,
		            @Param("cutoffDate") Date cutoffDate
		    );
}
