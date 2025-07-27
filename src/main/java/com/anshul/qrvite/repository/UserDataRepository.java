package com.anshul.qrvite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anshul.qrvite.model.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Integer> {

	UserData findByUsername(String username);
	
	boolean existsByUsername(String username);
	
    boolean existsByEmail(String email);
    
    boolean existsByContact(String contact);


}
