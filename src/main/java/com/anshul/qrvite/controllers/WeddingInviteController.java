package com.anshul.qrvite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anshul.qrvite.dto.WeddingDataDTO;
import com.anshul.qrvite.dto.WeddingEventsDTO;
import com.anshul.qrvite.model.WeddingData;
import com.anshul.qrvite.service.WeddingService;

@Controller //Rest service is not returning a rest response but return HTML
@RequestMapping("/marriage")
public class WeddingInviteController {
	

	WeddingEventsDTO weddingEventsDTO;
	
	@Autowired
	WeddingService weddingService;
	
	@PostMapping("/createMarriageInvite")
	@ResponseBody
	public WeddingData createMarriageInvite(@RequestBody WeddingDataDTO weddingDTO) {
		return weddingService.createInvite(weddingDTO);
	}
	
	@PutMapping("/updateMarriageInvite")
	@ResponseBody
	public WeddingData updateMarriageInvite(@RequestBody WeddingDataDTO weddingDTO) {
		return null;
	}
	
	@GetMapping("/getMarriageInviteForUser")
	@ResponseBody
	public WeddingData getMarriageInviteForUser() {
		return null;
	}
	
	

    
}
