package com.wishcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

//	public Optional<CartItem> findByCustomer_idAndProduct_id(Integer customerId, Integer productId);
//
//	public List<CartItem> findAllByCustomerOrderByCreatedDateDesc(User customer);

}
