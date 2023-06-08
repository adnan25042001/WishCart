package com.wishcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.Cart;
import com.wishcart.model.Product;
import com.wishcart.model.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

	Optional<Cart> findByUserAndProduct(User user, Product product);

	public List<Cart> findAllByUserOrderByAddedDateDesc(User user);

}
