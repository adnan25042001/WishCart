package com.wishcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer> {

	public Optional<Admin> findByEmail(String email);

}
