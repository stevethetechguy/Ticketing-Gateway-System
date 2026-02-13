package com.synergisticit.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;



public class TicketHistory {
	Long id;
	Ticket ticket;
	Action action;
	Employee actionBy;
	Date actionDate;
	String comments;
	
	
	public TicketHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TicketHistory(Long id, Ticket ticket, Action action, Employee actionBy, Date actionDate, String comments) {
		super();
		this.id = id;
		this.ticket = ticket;
		this.action = action;
		this.actionBy = actionBy;
		this.actionDate = actionDate;
		this.comments = comments;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Employee getActionBy() {
		return actionBy;
	}
	public void setActionBy(Employee actionBy) {
		this.actionBy = actionBy;
	}
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
