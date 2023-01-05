package com.wishcart.controller;

import java.util.List;

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

	private ProductService ps;

	@GetMapping("productbyname/{name}")
	public ResponseEntity<List<Product>> getProductByNameHandler(@PathVariable("name") String name) {
		return new ResponseEntity<List<Product>>(ps.getProductByName(name), HttpStatus.OK);
	}

}
