package com.anshul.qrvite.constants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PageConstants {
	public final String ROLE_ADMIN= "ADMIN";
    public final String LOGIN_PAGE = "login";
    public final String DASHBOARD_PAGE = "dashboard_user";
    public final String DASHBOARD_PAGE_ADMIN = "dashboard_admin"; //admin_dashboard
    public final String REDIRECT_IF_ALREADY_LOGGED_IN_USER = "redirect:/".concat(DASHBOARD_PAGE);
    public final String REDIRECT_IF_ALREADY_LOGGED_IN_ADMIN = "redirect:/".concat(DASHBOARD_PAGE);
    public final String SIGNUP_PAGE = "signup";
    public final String ERROR_404_PAGE = "error";
    public final String WEDDING_TEMPLATE3="cards/wedding/Wedding Event3";
    public final String WEDDING_TEMPLATE1="cards/wedding/Wedding Event1";
    public final String WEDDING_TEMPLATE2="cards/wedding/Wedding Event2";
    // Add as needed
    
    public Map<String, String> getQuestions() {
        Map<String, String> map = new HashMap<>();
        map.put("pet", "What is your pet's name?");
        map.put("birthplace", "What is your birthplace?");
        map.put("school", "What was the name of your first school?");
        map.put("favouriteFood", "What is your favourite food?");
        return map;
    }
}