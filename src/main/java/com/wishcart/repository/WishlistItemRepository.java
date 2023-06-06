package com.wishcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.WishlistItem;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {

}
