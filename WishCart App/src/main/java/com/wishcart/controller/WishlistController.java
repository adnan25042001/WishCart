package com.wishcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.SuccessMessage;
import com.wishcart.service.WishlistService;

@RestController
@RequestMapping("wishlist")
public class WishlistController {

	@Autowired
	private WishlistService ws;

	@PostMapping("/add/{productid}/{authkey}")
	public ResponseEntity<SuccessMessage> addToWishlistHandler(@PathVariable("productid") Integer productId,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(ws.addToWishlist(productId, authKey), HttpStatus.OK);
	}

	@DeleteMapping("/remove/{productid}/{authkey}")
	public ResponseEntity<SuccessMessage> removeFromWishlistHandler(@PathVariable("productid") Integer productId,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(ws.removeFromWishlist(productId, authKey), HttpStatus.OK);
	}

	@GetMapping("/all/{authkey}")
	public ResponseEntity<SuccessMessage> getWishlistHandler(@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(ws.getWishlistOfUser(authKey), HttpStatus.OK);
	}

}
