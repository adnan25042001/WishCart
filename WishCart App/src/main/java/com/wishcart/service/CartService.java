package com.wishcart.service;

import com.wishcart.exception.CartException;
import com.wishcart.exception.ProductException;
import com.wishcart.model.SuccessMessage;

public interface CartService {

	public SuccessMessage addToCart(Integer productId, String authKey) throws ProductException;

	public SuccessMessage updateProductQuantity(Integer productId, Integer quantity, String authKey)
			throws ProductException, CartException;

	public SuccessMessage removeFromCart(Integer productId, String authKey) throws ProductException, CartException;

	public SuccessMessage getCartItems(String authKey) throws CartException;

	public SuccessMessage removeAllCart(String authKey) throws CartException;

}
