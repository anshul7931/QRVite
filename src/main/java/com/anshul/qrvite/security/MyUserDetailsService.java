package com.anshul.qrvite.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anshul.qrvite.model.UserData;
import com.anshul.qrvite.repository.UserDataRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDataRepository userDataRepository;
	
	//TODO Exception Handle on wrong login
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			UserData userData = userDataRepository.findByUsername(username);
			
			return new User(userData.getUsername(),
					userData.getPassword(),
					Arrays.asList(new SimpleGrantedAuthority("ROLE_" + userData.getRole()))
			);
		
	}

	

	
}
