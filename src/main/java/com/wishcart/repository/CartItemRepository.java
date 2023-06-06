package com.wishcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
