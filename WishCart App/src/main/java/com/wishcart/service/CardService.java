package com.wishcart.service;

import java.util.List;

import com.wishcart.dto.CardDto;
import com.wishcart.exception.CardException;
import com.wishcart.model.Card;

public interface CardService {

	public String addCard(Card cardDetails, String authKey) throws CardException;

	public String removeCard(CardDto card, String authKey) throws CardException;

	public List<Card> getAllCardsOfUser(String authKey) throws CardException;

}
