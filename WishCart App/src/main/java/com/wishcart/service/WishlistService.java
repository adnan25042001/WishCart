package com.wishcart.service;

import java.util.List;

import com.wishcart.exception.ProductException;
import com.wishcart.exception.WishlistException;
import com.wishcart.model.Wishlist;

public interface WishlistService {

	public String addToWishlist(Integer productId, String authKey) throws ProductException, WishlistException;

	public String removeFromWishlist(Integer productId, String authKey) throws ProductException, WishlistException;

	public List<Wishlist> getWishlistOfUser(String authKey);
	
	

}
