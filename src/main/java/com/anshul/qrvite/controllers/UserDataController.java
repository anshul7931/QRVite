package com.anshul.qrvite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	//Add security questions as constants
	//Git hub, BCrypt, JWT
	//Dashboard using Thymeleaf Layout
	//getCardsForUser - hasMarriageCard, hasBirthdayCard,hasCorporateEventCard,hasFarewellOrFresherCard,id,username,password,role
	//Card - expiry, created date, qr size, qr color
	//Login - UI Validation for wrong password
	//Forgot Password - Test after Sign Up, Update method in controller with less lines(Modularize in service)
	//Add Card expiry dates
	//MyUserDetailService Exception Handle on wrong login
	//User Admin getRole() check and differentiate
	//QR modify for center logo
	//Login->Logout->Sign Up-> Back Button is opening Dashboard instead of Login
	
	//TODO Remove this UserDetail API - Create DTO
	@GetMapping("/admin/getAllUsers")
	@ResponseBody
	public List<UserData> getAllUsers(){
		return userDataRepository.findAll();
	}
	
	 @GetMapping("/")
     public String home(Model model) {
		 return userDataService.getDashboardPage(model);	 
     }
	
	@GetMapping("/dashboard")
    public String dashboard(Model model) {
		return userDataService.getDashboardPage(model);	
    }
}
