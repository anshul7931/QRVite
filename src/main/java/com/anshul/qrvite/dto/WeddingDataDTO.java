package com.anshul.qrvite.dto;

import java.sql.Date;
import java.util.List;



public class WeddingDataDTO {

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
	private String qrSize;
	private String qrColor;
	private String footerText;
	private List<WeddingEventsDTO> weddingEventsListDto;
	private String hasEvents;
	private UserDataDTO userData;
	private String hashTag;
	private Date created_date;
	private Date expiry_date;
	
	
	public String getQrSize() {
		return qrSize;
	}
	public void setQrSize(String qrSize) {
		this.qrSize = qrSize;
	}
	public String getQrColor() {
		return qrColor;
	}
	public void setQrColor(String qrColor) {
		this.qrColor = qrColor;
	}
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
	
	
	public String getHashTag() {
		return hashTag;
	}
	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	public List<WeddingEventsDTO> getWeddingEventsListDto() {
		return weddingEventsListDto;
	}
	public void setWeddingEventsListDto(List<WeddingEventsDTO> weddingEventsListDto) {
		this.weddingEventsListDto = weddingEventsListDto;
	}
	public UserDataDTO getUserData() {
		return userData;
	}
	public void setUserData(UserDataDTO userData) {
		this.userData = userData;
	}
	public String getHasEvents() {
		return hasEvents;
	}
	public void setHasEvents(String hasEvents) {
		this.hasEvents = hasEvents;
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
