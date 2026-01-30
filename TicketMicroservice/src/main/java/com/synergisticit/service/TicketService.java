package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.Ticket;
import com.synergisticit.repository.TicketRepository;

@Service
public class TicketService implements ITicketService {

	@Autowired
	TicketRepository ticketrepository;
	
	@Override
	public Ticket save(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketrepository.save(ticket);
	}

	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return ticketrepository.findAll();
	}

	@Override
	public Ticket findById(Long id) {
		// TODO Auto-generated method stub
		return ticketrepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Ticket with ID " + id + " not found"));
	}

	@Override
	public Ticket update(Long id, Ticket ticket) {
		// TODO Auto-generated method stub
		Ticket existingTicket = ticketrepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
		
		existingTicket.setCategory(ticket.getCategory());
		existingTicket.setDescription(ticket.getDescription());
		existingTicket.setFileAttachmentPath(ticket.getFileAttachmentPath());
		existingTicket.setHistory(ticket.getHistory());
		existingTicket.setPriority(ticket.getPriority());
		existingTicket.setStatus(ticket.getStatus());
		existingTicket.setTitle(ticket.getTitle());
		
		return ticketrepository.save(existingTicket);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		ticketrepository.deleteById(id);
	}

}
