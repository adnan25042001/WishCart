package com.wishcart.service;

import com.wishcart.exception.CartException;
import com.wishcart.exception.ProductException;
import com.wishcart.model.SuccessMessage;

public interface CartService {

	public SuccessMessage addToCart(Long productId, Integer quantity) throws ProductException;

	public SuccessMessage updateProductQuantity(Long productId, Integer quantity)
			throws ProductException, CartException;

	public SuccessMessage removeFromCart(Integer productId) throws ProductException, CartException;

	public SuccessMessage getCartItems() throws CartException;

	public SuccessMessage removeAllCart() throws CartException;

}
