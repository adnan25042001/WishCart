package com.wishcart.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.SuccessMessage;
import com.wishcart.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final ProductService productService;

	@GetMapping("/product")
	public ResponseEntity<SuccessMessage> getProductByIdHandler(@RequestParam("id") Long id, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getProductById(id, email), HttpStatus.OK);
	}

	@GetMapping("/product/search")
	public ResponseEntity<SuccessMessage> getProductByIdHandler(@RequestParam("q") String name, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getProductByName(name, email), HttpStatus.OK);
	}

	@GetMapping("/products/getall")
	public ResponseEntity<SuccessMessage> getAllProductHandler(Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getAllProducts(email), HttpStatus.OK);
	}

	@GetMapping("/product/betweenprice")
	public ResponseEntity<SuccessMessage> getProductBetweenPriceHandler(@RequestParam("min") Double min,
			@RequestParam("max") Double max) {
		return new ResponseEntity<SuccessMessage>(productService.getProductBetweenPrice(min, max), HttpStatus.OK);
	}

	@GetMapping("/products/bycategory")
	public ResponseEntity<SuccessMessage> getAllProductByCategoryHandler(@RequestParam("id") Long id,
			Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getProductsByCategoryId(id, email), HttpStatus.OK);
	}

}
