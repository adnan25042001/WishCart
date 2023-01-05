package com.wishcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.Cart;

public interface CartDao extends JpaRepository<Cart, Integer> {

}
