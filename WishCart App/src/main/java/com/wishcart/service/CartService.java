package com.wishcart.service;

import com.wishcart.dto.CartDto;
import com.wishcart.exception.CartException;
import com.wishcart.exception.ProductException;

public interface CartService {

	public String addToCart(Integer productId, String authKey) throws ProductException;

	public String updateProductQuantity(Integer productId, Integer quantity, String authKey)
			throws ProductException, CartException;

	public String removeFromCart(Integer productId, String authKey) throws ProductException, CartException;

	public CartDto getCartItems(String authKey) throws CartException;

	public String removeAllCart(String authKey) throws CartException;

}
