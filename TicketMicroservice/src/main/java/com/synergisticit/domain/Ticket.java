package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ticket {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String title;
	String description;
	@ManyToOne
	Employee createdBy;
	@ManyToOne
	Employee assignee;
	Priority priority;
	Status status;
	Date creationDate;
	String category;
	String fileAttachmentPath;
	
	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
	List<TicketHistory> history = new ArrayList<>();
	
	
}
