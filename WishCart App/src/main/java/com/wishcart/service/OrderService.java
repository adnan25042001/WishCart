package com.wishcart.service;

import java.util.List;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.wishcart.dto.CheckoutItemDto;

public interface OrderService {

	public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException;

}
