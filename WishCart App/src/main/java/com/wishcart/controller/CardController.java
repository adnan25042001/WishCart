package com.wishcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.dto.CardDto;
import com.wishcart.model.Card;
import com.wishcart.model.SuccessMessage;
import com.wishcart.service.CardService;

@RestController
public class CardController {

	@Autowired
	private CardService cs;

	@PostMapping("/addcard/{authkey}")
	public ResponseEntity<SuccessMessage> addCardHandler(@RequestBody Card card,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(cs.addCard(card, authKey), HttpStatus.CREATED);
	}

	@DeleteMapping("/removecard/{authkey}")
	public ResponseEntity<SuccessMessage> removeCardHandler(@RequestBody CardDto card,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(cs.removeCard(card, authKey), HttpStatus.CREATED);
	}

	@GetMapping("/getallcards/{authkey}")
	public ResponseEntity<SuccessMessage> getAllCardHandler(@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(cs.getAllCardsOfUser(authKey), HttpStatus.CREATED);
	}

}
