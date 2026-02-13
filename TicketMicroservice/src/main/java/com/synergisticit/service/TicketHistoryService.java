package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.synergisticit.domain.TicketHistory;
import com.synergisticit.repository.TicketHistoryRepository;

@Service
public class TicketHistoryService implements ITicketHistoryService {

	@Autowired
	TicketHistoryRepository ticketHistoryRepository;
	
	@Override
	public TicketHistory save(TicketHistory ticketHistory) {
		// TODO Auto-generated method stub
		return ticketHistoryRepository.save(ticketHistory);
	}

	@Override
	public List<TicketHistory> findAll() {
		// TODO Auto-generated method stub
		return ticketHistoryRepository.findAll();
	}

	@Override
	public TicketHistory findById(Long id) {
		// TODO Auto-generated method stub
		return ticketHistoryRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Ticket History with ID " + id + " not found"));
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		ticketHistoryRepository.deleteById(id);
	}
	public List<TicketHistory> getHistoryByTicketId(Long ticketId) {
        return ticketHistoryRepository.findByTicketIdOrderByActionDateDesc(ticketId);
    }
}
