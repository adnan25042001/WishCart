package com.wishcart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData;
import com.wishcart.dto.CheckoutItemDto;

@Service
public class OrderServiceImpl implements OrderService {

	@Value("${BASE_URL}")
	private String baseURL;

	@Value("${STRIPE_SECERET_KEY}")
	private String apiKey;

	@Override
	public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {

		String successURL = baseURL + "payment/success";

		String failureURL = baseURL + "payment/failed";

		Stripe.apiKey = apiKey;

		List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

		for (CheckoutItemDto cid : checkoutItemDtoList) {
			sessionItemList.add(createSessionLineItem(cid));
		}

		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT).setCancelUrl(failureURL).setSuccessUrl(successURL)
				.addAllLineItem(sessionItemList).build();

		return Session.create(params);

	}

	private LineItem createSessionLineItem(CheckoutItemDto cid) {

		return SessionCreateParams.LineItem.builder().setPriceData(createPriceData(cid))
				.setQuantity(Long.parseLong(String.valueOf(cid.getQuantity()))).build();

	}

	private PriceData createPriceData(CheckoutItemDto cid) {

		return SessionCreateParams.LineItem.PriceData.builder().setCurrency("inr")
				.setUnitAmount((Long) Math.round(cid.getPrice()))
				.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
						.setName(cid.getProductName()).build())
				.build();

	}

}
