package com.model;

import java.util.Date;

public class AgentTicketAssignment {
	private int agentId;
	private int ticketId;
	private Date assignmentDate;
	
	public AgentTicketAssignment(int agentId, int ticketId) {
		this.agentId = agentId;
		this.ticketId = ticketId;
	}
	
	public AgentTicketAssignment(int agentId, int ticketId, Date assignmentDate) {
		this.agentId = agentId;
		this.ticketId = ticketId;
		this.assignmentDate = assignmentDate;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Date getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	
	
	
	
	
}
