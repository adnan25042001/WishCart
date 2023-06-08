package com.wishcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wishcart.model.Product;
import com.wishcart.model.User;
import com.wishcart.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

	Optional<Wishlist> findByUserAndProduct(User user, Product product);

	List<Wishlist> findAllByUserOrderByAddedDateDesc(User user);

}
