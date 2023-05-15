package com.wishcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.SuccessMessage;
import com.wishcart.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cs;

	@PostMapping("/cart/add/{productid}/{authkey}")
	public ResponseEntity<SuccessMessage> addToCartHandler(@PathVariable("productid") Integer productId,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(cs.addToCart(productId, authKey), HttpStatus.CREATED);
	}

	@DeleteMapping("/cart/remove/{productid}/{authkey}")
	public ResponseEntity<SuccessMessage> removeFromCartHandler(@PathVariable("productid") Integer productId,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(cs.removeFromCart(productId, authKey), HttpStatus.OK);
	}

	@PutMapping("/cart/updatequantity/{productid}/{quantity}/{authkey}")
	public ResponseEntity<SuccessMessage> updateProductQuantityHandler(@PathVariable("productid") Integer productId,
			@PathVariable("quantity") Integer quantity, @PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(cs.updateProductQuantity(productId, quantity, authKey),
				HttpStatus.OK);
	}

	@GetMapping("/cart/getall/{authkey}")
	public ResponseEntity<SuccessMessage> getCartItemsHandler(@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(cs.getCartItems(authKey), HttpStatus.OK);
	}

	@DeleteMapping("/cart/removeall/{authkey}")
	public ResponseEntity<SuccessMessage> removeAllCartItemsHandler(@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(cs.removeAllCart(authKey), HttpStatus.OK);
	}

}
