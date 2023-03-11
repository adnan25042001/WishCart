package com.wishcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wishcart.model.Wishlist;

@Repository
public interface WishlistDao extends JpaRepository<Wishlist, Integer> {

	public List<Wishlist> findByCustomer_id(Integer customerId);

	public Optional<Wishlist> findByCustomer_idAndProduct_id(Integer customerId, Integer productId);

}
