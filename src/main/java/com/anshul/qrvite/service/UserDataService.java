package com.anshul.qrvite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.anshul.qrvite.constants.PageConstants;
import com.anshul.qrvite.dto.DashboardViewDTO;
import com.anshul.qrvite.dto.UserDataDTO;
import com.anshul.qrvite.model.UserData;
import com.anshul.qrvite.repository.UserDataRepository;

@Service
public class UserDataService {
	
	@Autowired
	UserDataRepository userDataRepository;
	
	@Autowired
	PageConstants pageConstants;
	
	
	/* Pre-Login - Authentication */

	@Transactional
    public String registerUser(UserDataDTO userDataDTO, Model model) {
    	
        String errorMsg = "";
        errorMsg = validateSignUpData(userDataDTO,model);
        if(!errorMsg.equals("")) {
        	 model.addAttribute("error", errorMsg);
             return pageConstants.SIGNUP_PAGE;
        }

        UserData user = new UserData();
        user.setUsername(userDataDTO.getUsername());
        user.setEmail(userDataDTO.getEmail());
        user.setContact(userDataDTO.getContact());
        user.setSecurityQuestion(userDataDTO.getSecurityQuestion());
        user.setSecurityAnswer(userDataDTO.getSecurityAnswer());
//        user.setPassword(passwordEncoder.encode(userDataDTO.getPassword()));
        user.setPassword(userDataDTO.getPassword());
        user.setRole("USER");

        userDataRepository.save(user);

        return "redirect:/login?signupSuccess";
    }

	

	private String validateSignUpData(UserDataDTO userDataDTO, Model model) {

		if (userDataRepository.existsByUsername(userDataDTO.getUsername())) {
            return "Username already exists.";
        }
        if (userDataRepository.existsByEmail(userDataDTO.getEmail())) {
            return "Email already registered.";
        }
        if (userDataRepository.existsByContact(userDataDTO.getContact())) {
            return "Contact Detail already registered.";
        }
        // Check password match
        if (!userDataDTO.getPassword().equals(userDataDTO.getConfirmPassword())) {
            return "Passwords do not match.";
        }
        if(userDataDTO.getPassword().length()<8) {
        	return "Minimum length of password is 8";
        }
        
		return "";
	}


	/* Post Login */

	public String getDashboardPage(Model model) {
		
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String username = authentication.getName(); 
		 UserData userData = userDataRepository.findByUsername(username);
		 
		 model.addAttribute("username", userData.getUsername().toUpperCase());
		 
		 List<DashboardViewDTO> cards = new ArrayList<>();

		    

		    cards.add(new DashboardViewDTO(
		        "Marriage Invite 1",
		        "Generate Marriage Invitation 1 quickly for testing.",
		        "/mockup/marriageInvite1",
		        false
		    ));

		    cards.add(new DashboardViewDTO(
		        "Marriage Invite 2",
		        "Generate Marriage Invitation 2 quickly for testing.",
		        "/mockup/marriageInvite2",
		        false
		    ));

		    cards.add(new DashboardViewDTO(
		        "Instagram Card",
		        "Generate Instagram optimized invite card dynamically.",
		        "/mockup/socialmedia/marriageInvite1",
		        false
		    ));

		    

		 
		 if(userData.getRole().equalsIgnoreCase(pageConstants.ROLE_ADMIN)) {
 			 cards.add(new DashboardViewDTO(
		         "Get All Users from Java",
		         "View and manage all registered users in the system efficiently.",
		         "/admin/getAllUsers",
 		         false
 		     ));
 
		     cards.add(new DashboardViewDTO(
		         "Swagger API Docs",
		         "Explore and test all REST APIs interactively using Swagger.",
		         "/swagger-ui/index.html",
		         true
		     ));
		     
		     cards.add(new DashboardViewDTO(
			         "Actuator",
			         "Explore and test all REST APIs interactively using Swagger.",
			         "/actuator",
			         true
			 ));
		     
		     cards.add(new DashboardViewDTO(
			         "Health",
			         "Explore and test all REST APIs interactively using Swagger.",
			         "/actuator/health",
			         true
			  ));
		     cards.add(new DashboardViewDTO(
			         "Info",
			         "Explore and test all REST APIs interactively using Swagger.",
			         "/actuator/info",
			         true
			  ));
		     cards.add(new DashboardViewDTO(
			         "Promethus",
			         "Explore and test all REST APIs interactively using Swagger.",
			         "/actuator/prometheus",
			         true
			 ));
			 model.addAttribute("cards", cards);
			 return pageConstants.DASHBOARD_PAGE_ADMIN;
		 }else {
			 model.addAttribute("cards", cards);
			 return pageConstants.DASHBOARD_PAGE; // maps to dashboard.html in /templates
		 }
	}
	
	
	

}
