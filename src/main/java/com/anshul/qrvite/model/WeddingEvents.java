package com.anshul.qrvite.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Component
public class WeddingEvents {

	@Id
	@GeneratedValue
	public Integer id;
	
	private String time;
    private String title;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "wedding_id")
    @JsonBackReference
    private WeddingData weddingData;

	public WeddingData getWeddingData() {
		return weddingData;
	}
	public void setWeddingData(WeddingData weddingData) {
		this.weddingData = weddingData;
	}
	
	public Integer getId() {
		return id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
