package com.wishcart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishcart.dto.CardDto;
import com.wishcart.exception.CardException;
import com.wishcart.exception.CustomerException;
import com.wishcart.model.Card;
import com.wishcart.model.CurrentUserSession;
import com.wishcart.model.Customer;
import com.wishcart.model.SuccessMessage;
import com.wishcart.repository.CardDao;
import com.wishcart.repository.CurrentUserSessionDao;
import com.wishcart.repository.CustomerDao;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDao carddao;

	@Autowired
	private CurrentUserSessionDao cusdao;

	@Autowired
	private CustomerDao customerdao;

	@Override
	public SuccessMessage addCard(Card card, String authKey) throws CardException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = customerdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("User not found"));

		card.setCustomer(customer);

		carddao.save(card);

		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<String> message = new ArrayList<>();
		message.add("card saved successfully");
		successMessage.setResult(message);

		return successMessage;
	}

	@Override
	public SuccessMessage removeCard(CardDto card, String authKey) throws CardException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = customerdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("User not found"));

		Card crd = carddao.findByCardHolderAndCardNumber(card.getCardHolder(), card.getCardNumber())
				.orElseThrow(() -> new CardException("card not found"));

		if (customer.getId() != crd.getCustomer().getId())
			throw new CardException("Invalid card details");

		carddao.delete(crd);

		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<String> message = new ArrayList<>();
		message.add("card removed successfully");
		successMessage.setResult(message);

		return successMessage;

	}

	@Override
	public SuccessMessage getAllCardsOfUser(String authKey) throws CardException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = customerdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("User not found"));

		List<Card> cards = carddao.findByCustomer(customer);

		if (cards.size() == 0)
			throw new CardException("No card available");

		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(cards.size());
		successMessage.setResult(cards);

		return successMessage;

	}

}
