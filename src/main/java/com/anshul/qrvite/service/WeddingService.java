package com.anshul.qrvite.service;

/*
 * 1. Entities should not be Autowired
 * 2. If class A reference B and B reference A in ManyToOne or OneToMany, then use:
 * @JsonBackReference: skip this side during serialization
 *  @JsonManagedReference: serialize this side
 * */

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anshul.qrvite.dto.WeddingDataDTO;
import com.anshul.qrvite.dto.WeddingEventsDTO;
import com.anshul.qrvite.model.UserData;
import com.anshul.qrvite.model.WeddingData;
import com.anshul.qrvite.model.WeddingEvents;
import com.anshul.qrvite.repository.UserDataRepository;
import com.anshul.qrvite.repository.WeddingDataRepository;

@Service
@Transactional
public class WeddingService {
	
	@Autowired
	WeddingDataRepository weddingDataRepository;
	
//	@Autowired
//	WeddingEvents weddingEvents;
	
	@Autowired
	WeddingData weddingData;
	
	@Autowired
	UserData userData;
	
	@Autowired
	UserDataRepository userDataRepository;

	public WeddingData createInvite(WeddingDataDTO weddingDTO) {
		DtoToEntity(weddingDTO);
		return weddingDataRepository.save(weddingData);
	}

	private void DtoToEntity(WeddingDataDTO weddingDTO) {
		if(weddingDTO.getCoupleName()!=null){
			weddingData.setCoupleName(weddingDTO.getCoupleName());
		}
		if(weddingDTO.getHashTag()!=null){
			weddingData.setHashTag(weddingDTO.getHashTag());
		}
		if(weddingDTO.getAddress()!=null){
			weddingData.setAddress(weddingDTO.getAddress());
		}
		if(weddingDTO.getEmail()!=null){
			weddingData.setEmail(weddingDTO.getEmail());
		}
		if(weddingDTO.getPhotoUrl()!=null){
			weddingData.setPhotoUrl(weddingDTO.getPhotoUrl());
		}
		if(weddingDTO.getDate()!=null){
			weddingData.setDate(weddingDTO.getDate());
		}
		if(weddingDTO.getTime()!=null){
			weddingData.setTime(weddingDTO.getTime());
		}
		if(weddingDTO.getVenue()!=null){
			weddingData.setVenue(weddingDTO.getVenue());
		}
		if(weddingDTO.getMapUrl()!=null){
			weddingData.setMapUrl(weddingDTO.getMapUrl());
		}
		if(weddingDTO.getRsvpDate()!=null){
			weddingData.setRsvpDate(weddingDTO.getRsvpDate());
		}
		if(weddingDTO.getPhone()!=null){
			weddingData.setPhone(weddingDTO.getPhone());
		}
		if(weddingDTO.getQrData()!=null){
			weddingData.setQrData(weddingDTO.getQrData());
		}
		if(weddingDTO.getFooterText()!=null){
			weddingData.setFooterText(weddingDTO.getFooterText());
		}
		
		//Set Events Data
		if(weddingDTO.getWeddingEventsListDto()!=null && weddingDTO.getWeddingEventsListDto().size()>0) {
			List<WeddingEvents> eventList = new ArrayList<>();
			for(WeddingEventsDTO event:weddingDTO.getWeddingEventsListDto()) {
				
				WeddingEvents weddingEvents = new WeddingEvents();
				weddingEvents.setDescription(event.getDescription());
				weddingEvents.setTime(event.getTime());
				weddingEvents.setTitle(event.getTitle());
				weddingEvents.setWeddingData(weddingData);
				
				eventList.add(weddingEvents);
			}
			weddingData.setWeddingEventsList(eventList);
			weddingData.setHasEvents("Y");
		}else {
			weddingData.setHasEvents("N");
		}
		
		//Set User Data
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		weddingData.setUserData(userDataRepository.findByUsername(username));
	}
	
	

}
