package com.wishcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.CurrentUserSession;

public interface CurrentUserSessionDao extends JpaRepository<CurrentUserSession, Integer>{

	public Optional<CurrentUserSession> findByEmail(String email);
	
	public Optional<CurrentUserSession> findByAuthKey(String authKey);
	
}
