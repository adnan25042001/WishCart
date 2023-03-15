package com.wishcart.service;

import com.wishcart.dto.CardDto;
import com.wishcart.exception.OrderException;
import com.wishcart.model.Order;

public interface OrderService {

	public Order placeOrder(CardDto card, String authKey) throws OrderException;

	public Order placeOrderByProductId(CardDto card, Integer id, String authKey) throws OrderException;

}
