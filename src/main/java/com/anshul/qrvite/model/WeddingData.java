package com.anshul.qrvite.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Component
public class WeddingData {
	
	@Id
	@GeneratedValue
	private Integer wedding_id;
	
	private String photoUrl;
	private String coupleName;
	private String date;
	private String time;
	private String venue;
	private String address;
	private String mapUrl;
	private String rsvpDate;
	private String email;
	private String phone;
	private String qrData;
	private String footerText;
	private String hasEvents;
	private String hashTag;
	private Date created_date;
	private Date expiry_date;
	
	
	
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}
	@ManyToOne
    @JoinColumn(name="userData_id", referencedColumnName = "id")
	private UserData userData;
	
	@OneToMany(mappedBy = "weddingData", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<WeddingEvents> weddingEventsList;
	
	
	public String getHashTag() {
		return hashTag;
	}
	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	
	public String getHasEvents() {
		return hasEvents;
	}
	public void setHasEvents(String hasEvents) {
		this.hasEvents = hasEvents;
	}
	public UserData getUserData() {
		return userData;
	}
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	public List<WeddingEvents> getWeddingEventsList() {
		return weddingEventsList;
	}
	public void setWeddingEventsList(List<WeddingEvents> weddingEventsList) {
		this.weddingEventsList = weddingEventsList;
	}
	
	public Integer getWedding_id() {
		return wedding_id;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getCoupleName() {
		return coupleName;
	}
	public void setCoupleName(String coupleName) {
		this.coupleName = coupleName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMapUrl() {
		return mapUrl;
	}
	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}
	public String getRsvpDate() {
		return rsvpDate;
	}
	public void setRsvpDate(String rsvpDate) {
		this.rsvpDate = rsvpDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQrData() {
		return qrData;
	}
	public void setQrData(String qrData) {
		this.qrData = qrData;
	}
	public String getFooterText() {
		return footerText;
	}
	public void setFooterText(String footerText) {
		this.footerText = footerText;
	}
	
	

}
