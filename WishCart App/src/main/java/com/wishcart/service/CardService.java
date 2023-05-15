package com.wishcart.service;

import com.wishcart.dto.CardDto;
import com.wishcart.exception.CardException;
import com.wishcart.model.Card;
import com.wishcart.model.SuccessMessage;

public interface CardService {

	public SuccessMessage addCard(Card cardDetails, String authKey) throws CardException;

	public SuccessMessage removeCard(CardDto card, String authKey) throws CardException;

	public SuccessMessage getAllCardsOfUser(String authKey) throws CardException;

}
