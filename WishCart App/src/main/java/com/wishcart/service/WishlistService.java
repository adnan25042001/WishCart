package com.wishcart.service;

import com.wishcart.exception.ProductException;
import com.wishcart.exception.WishlistException;
import com.wishcart.model.SuccessMessage;

public interface WishlistService {

	public SuccessMessage addToWishlist(Integer productId, String authKey) throws ProductException, WishlistException;

	public SuccessMessage removeFromWishlist(Integer productId, String authKey) throws ProductException, WishlistException;

	public SuccessMessage getWishlistOfUser(String authKey);
	
	

}
