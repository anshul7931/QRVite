package com.anshul.qrvite.constants;

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
}