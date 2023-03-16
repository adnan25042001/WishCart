package com.wishcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.dto.CardDto;
import com.wishcart.model.Order;
import com.wishcart.service.OrderService;

@RestController
@RequestMapping("/wishcart")
public class OrderController {
	
	@Autowired
	private OrderService orderservice;
	
	@PostMapping("/placeorder/{authkey}")
	public ResponseEntity<Order> placeOrderHandler(@RequestBody CardDto carddto, @PathVariable("authkey") String authkey){
		return new ResponseEntity<Order>(orderservice.placeOrder(carddto, authkey), HttpStatus.OK);
	}
	
	@PostMapping("/placeorderbyproductid/{productid}/{authkey}")
	public ResponseEntity<Order> placeOrderByProductIdHandler(@RequestBody CardDto carddto, @PathVariable("productid") Integer productid, @PathVariable("authkey") String authkey){
		return new ResponseEntity<Order>(
				orderservice.placeOrderByProductId(carddto, productid, authkey), HttpStatus.OK);
	}

}
