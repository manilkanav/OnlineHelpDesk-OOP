package com.model;

public class RefImage {
	private int imageId;
	private int ticketId;
	private String path;
	
	public RefImage(int ticketId, String path) {
		this.ticketId = ticketId;
		this.path = path;
	}
	

	public RefImage(int imageId, int ticketId, String path) {
		super();
		this.imageId = imageId;
		this.ticketId = ticketId;
		this.path = path;
	}



	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
