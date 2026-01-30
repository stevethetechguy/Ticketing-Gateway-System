package com.synergisticit.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TicketHistory {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@ManyToOne
    @JoinColumn
	Ticket ticket;
	String action;
	@ManyToOne
	Employee actionBy;
	Date actionDate;
	String comments;
}
