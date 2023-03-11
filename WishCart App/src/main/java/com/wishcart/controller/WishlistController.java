package com.wishcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.Wishlist;
import com.wishcart.service.WishlistService;

@RestController
@RequestMapping("/wishcart")
public class WishlistController {

	@Autowired
	private WishlistService ws;

	@PostMapping("/addtowishlist/{productid}/{authkey}")
	public ResponseEntity<String> addToWishlistHandler(@PathVariable("productid") Integer productId,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<String>(ws.addToWishlist(productId, authKey), HttpStatus.OK);
	}

	@GetMapping("/getwishlist/{authkey}")
	public ResponseEntity<List<Wishlist>> getWishlistHandler(@PathVariable("authkey") String authKey) {
		return new ResponseEntity<List<Wishlist>>(ws.getWishlistOfUser(authKey), HttpStatus.OK);
	}

}
