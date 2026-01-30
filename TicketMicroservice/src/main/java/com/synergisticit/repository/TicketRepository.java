package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.synergisticit.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
