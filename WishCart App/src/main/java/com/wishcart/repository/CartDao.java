package com.wishcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.Cart;
import com.wishcart.model.Customer;

public interface CartDao extends JpaRepository<Cart, Integer> {

	public Optional<Cart> findByCustomer_idAndProduct_id(Integer customerId, Integer productId);

	public List<Cart> findAllByCustomerOrderByCreatedDateDesc(Customer customer);

	public void deleteBycustomer(Customer customer);

}
