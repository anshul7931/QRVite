package com.anshul.qrvite.dto;


public class WeddingEventsDTO {

	private String time;
    private String title;
    private String description;

   

	public void setTime(String time) {
		this.time = time;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTime() { return time; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
