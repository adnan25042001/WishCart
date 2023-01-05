package com.wishcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByEmail(String email);

	public abstract List<Customer> findByAddress(String address);

	public abstract List<Customer> findByName(String name);

}
