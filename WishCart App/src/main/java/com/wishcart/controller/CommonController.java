package com.wishcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.Product;
import com.wishcart.service.ProductService;

@RestController
@RequestMapping("/wishcart")
public class CommonController {

	@Autowired
	private ProductService ps;

	@GetMapping("productbyname/{name}")
	public ResponseEntity<List<Product>> getProductByNameHandler(@PathVariable("name") String name) {
		return new ResponseEntity<List<Product>>(ps.getProductByName(name), HttpStatus.OK);
	}

	@GetMapping("productbetweenprice/{minprice}/{maxprice}")
	public ResponseEntity<List<Product>> getProductByNameHandler(@PathVariable("minprice") Double minPrice,
			@PathVariable("maxprice") Double maxPrice) {
		return new ResponseEntity<List<Product>>(ps.getProductBetweenPrice(minPrice, maxPrice), HttpStatus.OK);
	}

	@GetMapping("/allproduct")
	public ResponseEntity<List<Product>> getAllProductHandler() {
		return new ResponseEntity<List<Product>>(ps.getAllProducts(), HttpStatus.OK);
	}

}
