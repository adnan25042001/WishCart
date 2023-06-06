package com.wishcart.service;

import com.wishcart.exception.ProductException;
import com.wishcart.exception.WishlistException;
import com.wishcart.model.SuccessMessage;

public interface WishlistService {

	public SuccessMessage addToWishlist(Long productId) throws ProductException, WishlistException;

	public SuccessMessage removeFromWishlist(Long productId) throws ProductException, WishlistException;

	public SuccessMessage getWishlistOfUser();
	
	public SuccessMessage addToCart(Long productId) throws ProductException;

}
