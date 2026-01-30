package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.TicketHistory;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {

}
