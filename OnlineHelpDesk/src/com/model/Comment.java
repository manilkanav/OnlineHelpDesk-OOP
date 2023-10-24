package com.model;

import java.util.Date;

public class Comment {

	private int commentId;
	private int ticketId;
	private int user_id;
	private int agent_id;
	private String commentText;
	private Date timestamp;
	
	public Comment(int commentId, int ticketId, String commentText) {
		this.commentId = commentId;
		this.ticketId = ticketId;
		this.commentText = commentText;
	}

	public Comment(int commentId, int ticketId, String commentText, Date timestamp) {
		this.commentId = commentId;
		this.ticketId = ticketId;
		this.commentText = commentText;
		this.timestamp = timestamp;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(int agent_id) {
		this.agent_id = agent_id;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
