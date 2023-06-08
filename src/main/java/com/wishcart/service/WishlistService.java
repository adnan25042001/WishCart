package com.wishcart.service;

import com.wishcart.exception.ProductException;
import com.wishcart.exception.WishlistException;
import com.wishcart.model.SuccessMessage;

public interface WishlistService {

	public SuccessMessage addToWishlist(Long productId, String email) throws ProductException, WishlistException;

	public SuccessMessage removeFromWishlist(Long wishlistId, String email) throws ProductException, WishlistException;

	public SuccessMessage getWishlistOfUser(String email);
	
	public SuccessMessage addToCart(Long productId, String email) throws ProductException;

}
