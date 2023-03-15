package com.wishcart.service;

import java.util.List;

import com.wishcart.dto.removeCardDto;
import com.wishcart.exception.CardException;
import com.wishcart.model.Card;

public interface CardService {

	public String addCard(Card cardDetails, String authKey) throws CardException;

	public String removeCard(removeCardDto card, String authKey) throws CardException;

	public List<Card> getAllCardsOfUser(String authKey) throws CardException;

}
