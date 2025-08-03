package com.anshul.qrvite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //This annotation makes this config as the default spring security flow
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
	    http.csrf(customizer -> customizer.disable());
	    
	    http.authorizeHttpRequests(request -> request.antMatchers(
	    	        "/signup",
	    	        "/forgot-password"
	    	    ).permitAll()
	            .antMatchers(
	            		"/admin/**",
	            		"/v3/api-docs/**",
		    	        "/swagger-ui/**",
		    	        "/swagger-ui.html",
		    	        "/swagger-resources/**",
		    	        "/webjars/**",
		    	        "/actuator/*")
	            .hasRole("ADMIN")
//	            .antMatchers("/user/**").hasRole("USER")
	    	    .anyRequest()
	    	    .authenticated()// Require authentication for all other endpoints
	    );


	    http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true) 
                .permitAll());
	    
	    http.logout(logout -> logout.permitAll());

	    
	    http.httpBasic(Customizer.withDefaults());
	    

	    return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

	    provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
	    provider.setUserDetailsService(userDetailsService);//Create custom User Detail Service

	    return provider;
	}
	

}
