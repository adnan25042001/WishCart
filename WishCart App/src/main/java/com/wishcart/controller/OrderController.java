package com.wishcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.dto.CardDto;
import com.wishcart.model.SuccessMessage;
import com.wishcart.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderservice;

	@PostMapping("/placeorder/{authkey}")
	public ResponseEntity<SuccessMessage> placeOrderHandler(@RequestBody CardDto carddto,
			@PathVariable("authkey") String authkey) {
		return new ResponseEntity<SuccessMessage>(orderservice.placeOrder(carddto, authkey), HttpStatus.OK);
	}

	@PostMapping("/placeorderbyproductid/{productid}/{authkey}")
	public ResponseEntity<SuccessMessage> placeOrderByProductIdHandler(@RequestBody CardDto carddto,
			@PathVariable("productid") Integer productid, @PathVariable("authkey") String authkey) {
		return new ResponseEntity<SuccessMessage>(orderservice.placeOrderByProductId(carddto, productid, authkey),
				HttpStatus.OK);
	}

}
