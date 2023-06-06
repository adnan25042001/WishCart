package com.wishcart.service;

import org.springframework.stereotype.Service;

import com.wishcart.exception.CartException;
import com.wishcart.exception.ProductException;
import com.wishcart.model.SuccessMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	@Override
	public SuccessMessage addToCart(Long productId, Integer quantity) throws ProductException {

		return null;
	}

	@Override
	public SuccessMessage updateProductQuantity(Long productId, Integer quantity)
			throws ProductException, CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage removeFromCart(Integer productId) throws ProductException, CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage getCartItems() throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage removeAllCart() throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

}
