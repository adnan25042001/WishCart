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

import com.wishcart.dto.CartDto;
import com.wishcart.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cs;

	@PostMapping("/cart/add/{productid}/{authkey}")
	public ResponseEntity<String> addToCartHandler(@PathVariable("productid") Integer productId,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<String>(cs.addToCart(productId, authKey), HttpStatus.CREATED);
	}

	@DeleteMapping("/cart/remove/{productid}/{authkey}")
	public ResponseEntity<String> removeFromCartHandler(@PathVariable("productid") Integer productId,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<String>(cs.removeFromCart(productId, authKey), HttpStatus.OK);
	}

	@PutMapping("/cart/updatequantity/{productid}/{quantity}/{authkey}")
	public ResponseEntity<String> updateProductQuantityHandler(@PathVariable("productid") Integer productId,
			@PathVariable("quantity") Integer quantity, @PathVariable("authkey") String authKey) {
		return new ResponseEntity<String>(cs.updateProductQuantity(productId, quantity, authKey), HttpStatus.OK);
	}

	@GetMapping("/cart/getall/{authkey}")
	public ResponseEntity<CartDto> getCartItemsHandler(@PathVariable("authkey") String authKey) {
		return new ResponseEntity<CartDto>(cs.getCartItems(authKey), HttpStatus.OK);
	}

	@DeleteMapping("/cart/removeall/{authkey}")
	public ResponseEntity<String> removeAllCartItemsHandler(@PathVariable("authkey") String authKey) {
		return new ResponseEntity<String>(cs.removeAllCart(authKey), HttpStatus.OK);
	}

}
