package com.wishcart.service;

import com.wishcart.dto.CardDto;
import com.wishcart.exception.OrderException;
import com.wishcart.model.SuccessMessage;

public interface OrderService {

	public SuccessMessage placeOrder(CardDto card, String authKey) throws OrderException;

	public SuccessMessage placeOrderByProductId(CardDto card, Integer id, String authKey) throws OrderException;

}
