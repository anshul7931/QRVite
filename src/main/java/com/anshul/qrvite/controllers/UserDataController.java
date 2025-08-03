package com.anshul.qrvite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anshul.qrvite.constants.PageConstants;
import com.anshul.qrvite.model.UserData;
import com.anshul.qrvite.repository.UserDataRepository;
import com.anshul.qrvite.service.UserDataService;

@Controller
public class UserDataController {
	
	@Autowired
	UserDataRepository userDataRepository;
	
	@Autowired
	UserDataService userDataService;
	
	@Autowired
	PageConstants pageConstants;
		
	//Test Sign Up, Forgot Password, Sign In
	//Add security questions as constants - Verify the compelete flow once. Sign Up default question is not selecting
	//Git hub, BCrypt, JWT
	//getCardsForUser - hasMarriageCard, hasBirthdayCard,hasCorporateEventCard,hasFarewellOrFresherCard,id,username,password,role
	//Card Types table, a user can have multiple card types - Add this static Data in DB
	//Card - expiry, created date
	//Login - UI Validation for wrong password
	//Forgot Password - Test after Sign Up, Update method in controller with less lines(Modularize in service)
	//MyUserDetailService Exception Handle on wrong login
	//QR modify for center logo
	//Login->Logout->Sign Up-> Back Button is opening Dashboard instead of Login
	//Dashboard is loading after logout and back button click

	
	//TODO Remove this UserDetail API - Create DTO
	@GetMapping("/admin/getAllUsers")
	@ResponseBody
	public List<UserData> getAllUsers(){
		return userDataRepository.findAll();
	}
	
	 @GetMapping("/")
     public String home(Model model,Authentication authentication) {
		 if (authentication != null && authentication.isAuthenticated()) {
				return userDataService.getDashboardPage(model);	
			}else {
				return pageConstants.LOGIN_PAGE;
			} 
     }
	
	@GetMapping("/dashboard")
    public String dashboard(Model model,Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			return userDataService.getDashboardPage(model);	
		}else {
			return pageConstants.LOGIN_PAGE;
		}
    }
}
