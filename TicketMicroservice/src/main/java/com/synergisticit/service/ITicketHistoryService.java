package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.TicketHistory;

@Service
public interface ITicketHistoryService {
	TicketHistory save(TicketHistory ticketHistory);
	List<TicketHistory> findAll();
	TicketHistory findById(Long id);
	void delete(Long id);
}
