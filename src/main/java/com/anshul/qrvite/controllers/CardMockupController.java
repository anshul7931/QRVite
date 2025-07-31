package com.anshul.qrvite.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anshul.qrvite.constants.PageConstants;
import com.anshul.qrvite.dto.WeddingEventsDTO;

@Controller //Rest service is not returning a rest response but return HTML
@RequestMapping("/mockup")
public class CardMockupController {
	
	@Autowired
	PageConstants pageConstants;
	
	WeddingEventsDTO weddingEventsDTO;
	
		//TODO Add Hashtag also
		@GetMapping("/marriageInvite1")
	    public String marriageInvite(Model model) {
			model.addAttribute("photoUrl", "https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=140&q=80");
	        model.addAttribute("coupleName", "Rahul & Tina");
	        model.addAttribute("hashtag","#Retina");
	        model.addAttribute("date", "Satxurday, August 24, 2025");
	        model.addAttribute("time", "4:00 PM onwards");
	        model.addAttribute("venue", "Rose Garden Hall");
	        model.addAttribute("address", "789 Bloom Street, Los Angeles, CA");
	        model.addAttribute("mapUrl", "https://maps.google.com/?q=Rose+Garden+Hall+Los+Angeles");
	        model.addAttribute("rsvpDate", "August 10, 2025");
	        model.addAttribute("email", "weddingrsvp@example.com");
	        model.addAttribute("phone", "(321) 654-9870");
	        model.addAttribute("qrData", "https://via.placeholder.com/100x100.png?text=QR+Code");
	        model.addAttribute("qrSize", "300");
	        model.addAttribute("qrColor", "bw");
	        model.addAttribute("footerText", "We can’t wait to celebrate this special day with you 💕");

	        return pageConstants.WEDDING_TEMPLATE1;
	    }
		
		@GetMapping("/marriageInvite2")
	    public String getElegantWedding(Model model) {
	        model.addAttribute("photoUrl", "https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=140&q=80");
	        model.addAttribute("coupleName", "Sophia Taylor & Michael Johnson");
	        model.addAttribute("hashtag","#Somich");
	        model.addAttribute("date", "Saturday, October 10, 2025");
	        model.addAttribute("time", "3:00 PM onwards");
	        model.addAttribute("venue", "The Grand Rose Ballroom");
	        model.addAttribute("address", "456 Blossom Lane, Cityville");
	        model.addAttribute("mapUrl", "https://maps.google.com/?q=The+Grand+Rose+Ballroom");

	        // List of events for schedule
	        List<WeddingEventsDTO> events = new ArrayList<>();
	        weddingEventsDTO = new WeddingEventsDTO();
	        weddingEventsDTO.setTime("2:00 PM");
	        weddingEventsDTO.setTitle("Wedding Ceremony");
	        weddingEventsDTO.setDescription("Exchange of vows and rings in the Rose Garden.");
	        events.add(weddingEventsDTO);
	        
	        weddingEventsDTO = new WeddingEventsDTO();
	        weddingEventsDTO.setTime("5:00 PM");
	        weddingEventsDTO.setTitle("Wedding Ceremony 2");
	        weddingEventsDTO.setDescription("Exchange of vows and rings again.");
	        events.add(weddingEventsDTO);
	        
	        weddingEventsDTO = new WeddingEventsDTO();
	        weddingEventsDTO.setTime("8:00 PM");
	        weddingEventsDTO.setTitle("Wedding Ceremony 3");
	        weddingEventsDTO.setDescription("Exchange of vows and rings again and again.");
	        events.add(weddingEventsDTO);
	        
	        model.addAttribute("events", events);

	        model.addAttribute("rsvpDate", "September 20, 2025");
	        model.addAttribute("email", "sophia.michael.wedding@example.com");
	        model.addAttribute("phone", "(987) 654-3210");
	        model.addAttribute("qrData", "google.com");
	        model.addAttribute("qrSize", "300");
	        model.addAttribute("qrColor", "bw");
	        model.addAttribute("footerText", "Together with our families, we look forward to celebrating with you 💕");

	        return pageConstants.WEDDING_TEMPLATE2;
	    }
		
		@GetMapping("/socialmedia/marriageInvite1")
		public String instagramCard(Model model) {
			model.addAttribute("photoUrl", "https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=140&q=80");
			model.addAttribute("coupleName", "Rahul & Tina");
			model.addAttribute("date", "Saturday, August 24, 2025");
			model.addAttribute("time", "4:00 PM onwards");
			model.addAttribute("venue", "Rose Garden Hall");
			model.addAttribute("address", "789 Bloom Street, Los Angeles, CA");
//			model.addAttribute("mapUrl", "https://maps.google.com/?q=Rose+Garden+Hall+Los+Angeles");
			model.addAttribute("hashtag","#Retina");
			model.addAttribute("email", "weddingrsvp@example.com");
			model.addAttribute("phone", "(321) 654-9870");
			model.addAttribute("qrData", "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=https://maps.google.com/?q=Rose+Garden+Hall+Los+Angeles");
			model.addAttribute("qrSize", "300");
	        model.addAttribute("qrColor", "gradient");
			model.addAttribute("footerText", "We can’t wait to celebrate this special day with you 💕");

	        return pageConstants.WEDDING_TEMPLATE3;
		}

}
