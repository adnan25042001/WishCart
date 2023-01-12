package com.wishcart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.Product;
import com.wishcart.service.ProductService;

@RestController
@RequestMapping("/wishcart")
public class AdminController {

	@Autowired
	private ProductService ps;

	@PostMapping("/addproduct/{authkey}/{cat_id}")
	public ResponseEntity<Product> addProductHandler(@RequestBody @Valid Product product,
			@PathVariable("cat_id") Integer cat_id, @PathVariable("authkey") String authKey) {
		return new ResponseEntity<Product>(ps.addProduct(product, cat_id, authKey), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteproduct/{authkey}/{id}")
	public ResponseEntity<Product> addProductHandler(@PathVariable("id") Integer id,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<Product>(ps.removeProduct(id, authKey), HttpStatus.OK);
	}

	@GetMapping("productbyid/{authkey}/{id}")
	public ResponseEntity<Product> getProductByIdHandler(@PathVariable("id") Integer id,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<Product>(ps.getProductById(id, authKey), HttpStatus.OK);
	}

	@PostMapping("updateproduct/{authkey}")
	public ResponseEntity<Product> updateProductHandler(@RequestBody @Valid Product product,
			@PathVariable("authKey") String authKey) {
		return new ResponseEntity<Product>(ps.updateProduct(product, authKey), HttpStatus.CREATED);
	}

	@PutMapping("updateproductprice/{authkey}/{id}/{price}")
	public ResponseEntity<Product> updateProductPriceHandler(@PathVariable("id") Integer id,
			@PathVariable("price") Double price, @PathVariable("authKey") String authKey) {
		return new ResponseEntity<Product>(ps.updateProductPrice(id, price, authKey), HttpStatus.CREATED);
	}

}
