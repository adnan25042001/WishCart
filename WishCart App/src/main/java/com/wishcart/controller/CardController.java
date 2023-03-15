package com.wishcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.dto.removeCardDto;
import com.wishcart.model.Card;
import com.wishcart.service.CardService;

@RestController
@RequestMapping("/wishcart")
public class CardController {

	@Autowired
	private CardService cs;

	@PostMapping("/addcard/{authkey}")
	public ResponseEntity<String> addCardHandler(@RequestBody Card card, @PathVariable("authkey") String authKey) {
		return new ResponseEntity<String>(cs.addCard(card, authKey), HttpStatus.CREATED);
	}

	@DeleteMapping("/removecard/{authkey}")
	public ResponseEntity<String> removeCardHandler(@RequestBody removeCardDto card,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<String>(cs.removeCard(card, authKey), HttpStatus.CREATED);
	}

	@GetMapping("/getallcards/{authkey}")
	public ResponseEntity<List<Card>> getAllCardHandler(@PathVariable("authkey") String authKey) {
		return new ResponseEntity<List<Card>>(cs.getAllCardsOfUser(authKey), HttpStatus.CREATED);
	}

}
