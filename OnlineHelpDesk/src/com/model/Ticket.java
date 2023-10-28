package com.model;

import java.util.Date;

public class Ticket {
	private int ticketId;
	private int userId;
	private String subject;
	private String description;
	private String status;
	private String type;
	private Date createAt;
	private Date resolvedAt;
	
	public Ticket(int userId, String subject, String description, String status, String type) {
		this.userId = userId;
		this.subject = subject;
		this.description = description;
		this.status = status;
		this.type = type;
	}

	public Ticket(int ticketId, int userId, String subject, String description, String status, String type,
			Date createAt, Date resolvedAt) {
		this.ticketId = ticketId;
		this.userId = userId;
		this.subject = subject;
		this.description = description;
		this.status = status;
		this.type = type;
		this.createAt = createAt;
		this.resolvedAt = resolvedAt;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getResolvedAt() {
		return resolvedAt;
	}

	public void setResolvedAt(Date resolvedAt) {
		this.resolvedAt = resolvedAt;
	}
	
}
