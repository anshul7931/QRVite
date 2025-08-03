package com.anshul.qrvite.service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/* Pre-Login - Authentication */

	@Transactional
    public String registerUser(UserDataDTO userDataDTO, Model model) {
    	
        String errorMsg = "";
        errorMsg = validateSignUpData(userDataDTO,model);
        if(!errorMsg.equals("")) {
        	 model.addAttribute("error", errorMsg);
        	 model.addAttribute("securityQuestions", pageConstants.getQuestions());
             return pageConstants.SIGNUP_PAGE;
        }

        UserData user = new UserData();
        user.setUsername(userDataDTO.getUsername());
        user.setEmail(userDataDTO.getEmail());
        user.setContact(userDataDTO.getContact());
        user.setSecurityQuestion(userDataDTO.getSecurityQuestion());
        user.setSecurityAnswer(userDataDTO.getSecurityAnswer());
        user.setPassword(passwordEncoder.encode(userDataDTO.getPassword()));
        user.setRole("USER");

        userDataRepository.save(user);

        return "redirect:/login?signupSuccess";
    }

	@Transactional
	public String resetPassword(Model model, UserDataDTO userDataDTO) {
		
		UserData userData = userDataRepository.findByUsername(userDataDTO.getUsername());
		String errorMsg = "";
        errorMsg = validateResetPassword(model,userData,userDataDTO);
        
        if(!errorMsg.equals("")) {
        	model.addAttribute("securityQuestions", pageConstants.getQuestions());
            model.addAttribute("forgotPasswordError", errorMsg);
            model.addAttribute("showForgotModal", true);
            return "login";
        }
		
        userData.setPassword(passwordEncoder.encode(userDataDTO.getPassword())); // Use encoder in real use
        userDataRepository.save(userData);

        model.addAttribute("securityQuestions", pageConstants.getQuestions());
        model.addAttribute("forgotPasswordSuccess", "Password reset successful.");
        model.addAttribute("showForgotModal", true);
        return "login";
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
        
        return validatePassword(userDataDTO.getPassword());
        
	}
	

	private String validateResetPassword(Model model, UserData userData, UserDataDTO userDataDTO) {
		
		if (userData == null) {
        	return "User not found.";  
        }
        if (!userData.getSecurityQuestion().equalsIgnoreCase(userDataDTO.getSecurityQuestion())) {
        	return "Security question/answer do not match.";
        }
        if (!userData.getSecurityAnswer().equalsIgnoreCase(userDataDTO.getSecurityAnswer())) {
            	return "Security question/answer do not match.";
            }
        if (!userDataDTO.getPassword().equals(userDataDTO.getConfirmPassword())) {
        	return "Passwords did not match.";
        }
        
        return validatePassword(userDataDTO.getPassword());
	}


	private String validatePassword(String password) {
		if(password.length()<8) {
        	return "Minimum length of password is 8";
        }
		if(!password.contains("@")) {
			return "Password must contain @";
		}
		
		//Password must contain a 
		//On /forgot-password api call, /login should be called
        
		return "";
	}
	/* Post Login */

	public String getDashboardPage(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName(); 
		UserData userData = userDataRepository.findByUsername(username);
		
		model.addAttribute("username", userData.getUsername().toUpperCase());
		 
		Map<String, List<DashboardViewDTO>> categorizedCards = new LinkedHashMap<>();
	
		categorizedCards.put("Wedding Cards", Arrays.asList(
		    new DashboardViewDTO("Marriage Invite 1", "Generate Marriage Invitation 1 quickly.", "/mockup/marriageInvite1", false),
		    new DashboardViewDTO("Marriage Invite 2", "Elegant marriage card design.", "/mockup/marriageInvite2", false),
		    new DashboardViewDTO("Marriage Invite 3", "Elegant marriage card design.", "/mockup/socialmedia/marriageInvite1", false)
		));
	
		
	
		
		if(userData.getRole().equalsIgnoreCase(pageConstants.ROLE_ADMIN)) {
			
			categorizedCards.put("Actuators", Arrays.asList(
				    new DashboardViewDTO("Actuator", "View user login activity.", "/actuator", true),
				    new DashboardViewDTO("Health", "Check system-level logs.", "/actuator/health", true),
				    new DashboardViewDTO("Info", "View user login activity.", "/actuator/info", true),
				    new DashboardViewDTO("Promethus", "Check system-level logs.", "/actuator/prometheus", true)
			));
			
			categorizedCards.put("Admin APIs", Arrays.asList(
				    new DashboardViewDTO("All User Data", "View user login activity.", "/admin/getAllUsers", false),
				    new DashboardViewDTO("Swagger", "Check system-level logs.", "/swagger-ui/index.html", true)
			));
			
			model.addAttribute("categorizedCards", categorizedCards);
			return pageConstants.DASHBOARD_PAGE_ADMIN;
		 }else {
			 model.addAttribute("categorizedCards", categorizedCards);
			 return pageConstants.DASHBOARD_PAGE; // maps to dashboard.html in /templates
		}
	}


	
	

}
