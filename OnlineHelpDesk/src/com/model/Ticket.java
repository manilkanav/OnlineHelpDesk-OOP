package com.model;

import java.util.Date;

public class Ticket {
	private int ticketId;
	private int userIid;
	private String subject;
	private String description;
	private String status;
	private String type;
	private Date createAt;
	private Date resolvedAt;
	
	public Ticket(int userIid, String subject, String description, String status, String type) {
		this.userIid = userIid;
		this.subject = subject;
		this.description = description;
		this.status = status;
		this.type = type;
	}

	public Ticket(int ticketId, int userIid, String subject, String description, String status, String type,
			Date createAt) {
		super();
		this.ticketId = ticketId;
		this.userIid = userIid;
		this.subject = subject;
		this.description = description;
		this.status = status;
		this.type = type;
		this.createAt = createAt;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getUserIid() {
		return userIid;
	}

	public void setUserIid(int userIid) {
		this.userIid = userIid;
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
