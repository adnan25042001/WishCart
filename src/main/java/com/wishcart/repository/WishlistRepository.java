package com.wishcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wishcart.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

//	public List<WishlistItem> findByCustomer_id(Integer customerId);
//
//	public Optional<WishlistItem> findByCustomer_idAndProduct_id(Integer customerId, Integer productId);

}
