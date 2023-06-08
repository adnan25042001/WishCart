package com.wishcart.service;

import com.wishcart.exception.CartException;
import com.wishcart.exception.ProductException;
import com.wishcart.model.SuccessMessage;

public interface CartService {

	public SuccessMessage addToCart(Long productId, Integer quantity, String email) throws ProductException;

	public SuccessMessage updateProductQuantity(Long productId, Integer quantity, String email)
			throws ProductException, CartException;

	public SuccessMessage removeFromCart(Long cartId, String email) throws ProductException, CartException;

	public SuccessMessage getAllCartItems(String email) throws CartException;

	public SuccessMessage removeAllCart(String email) throws CartException;

}
