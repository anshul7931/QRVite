package com.anshul.qrvite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anshul.qrvite.constants.PageConstants;
import com.anshul.qrvite.dto.UserDataDTO;
import com.anshul.qrvite.model.UserData;
import com.anshul.qrvite.repository.UserDataRepository;
import com.anshul.qrvite.service.UserDataService;


@Controller
public class UserAuthenticationController {
	
	@Autowired
	PageConstants pageConstants;

	@Autowired
	UserDataRepository userDataRepository;
	
	@Autowired
	UserDataService userDataService;
	
	@GetMapping("/login")
    public String login(Model model,Authentication authentication) {
		//If user is on dashboard and clicks back, he should not navigate to login screen
        if (authentication != null && authentication.isAuthenticated()) {//If user token is still valid
   		 	return pageConstants.REDIRECT_IF_ALREADY_LOGGED_IN; 
        }
        
	    model.addAttribute("securityQuestions", pageConstants.getQuestions());
	    model.addAttribute("showForgotModal", false); // default false
        return pageConstants.LOGIN_PAGE;
    }
	
	
	@GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("userDataDTO", new UserDataDTO());
        model.addAttribute("securityQuestions", pageConstants.getQuestions());
        return pageConstants.SIGNUP_PAGE;
    }

    @PostMapping("/signup")
    public String processSignUp(@ModelAttribute("userDataDTO") UserDataDTO userDataDTO,Model model) {
        return userDataService.registerUser(userDataDTO, model);
    }
	
    @PostMapping("/forgot-password")
    public String handleForgotPassword(
            @RequestParam String username,
            @RequestParam String securityQuestion,
            @RequestParam String securityAnswer,
            @RequestParam String newPassword,
            @RequestParam String confirmNewPassword,
            Model model) {

        UserData userData = userDataRepository.findByUsername(username);
        
        if (userData == null) {
        	model.addAttribute("securityQuestions", pageConstants.getQuestions());
            model.addAttribute("forgotPasswordError", "User not found.");
            model.addAttribute("showForgotModal", true);
            return "login";
        }

        if (!userData.getSecurityQuestion().equalsIgnoreCase(securityQuestion) ||
            !userData.getSecurityAnswer().equalsIgnoreCase(securityAnswer)) {
        	model.addAttribute("securityQuestions", pageConstants.getQuestions());
            model.addAttribute("forgotPasswordError", "Security question/answer do not match.");
            model.addAttribute("showForgotModal", true);
            return "login";
        }

        if (!newPassword.equals(confirmNewPassword)) {
        	model.addAttribute("securityQuestions", pageConstants.getQuestions());
            model.addAttribute("forgotPasswordError", "Passwords did not match.");
            model.addAttribute("showForgotModal", true);
            return "login";
        }

        userData.setPassword(newPassword); // Use encoder in real use
        userDataRepository.save(userData);

        model.addAttribute("forgotPasswordSuccess", "Password reset successful.");
        model.addAttribute("showForgotModal", true);
        return "login";
    }


	
    
}
