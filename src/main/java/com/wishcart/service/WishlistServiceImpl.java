package com.wishcart.service;

import org.springframework.stereotype.Service;

import com.wishcart.exception.ProductException;
import com.wishcart.exception.WishlistException;
import com.wishcart.model.SuccessMessage;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Override
	public SuccessMessage addToWishlist(Long productId) throws ProductException, WishlistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage removeFromWishlist(Long productId) throws ProductException, WishlistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage getWishlistOfUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage addToCart(Long productId) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

}
