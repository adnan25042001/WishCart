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
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.dto.ProductDto;
import com.wishcart.model.SuccessMessage;
import com.wishcart.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService ps;

	@GetMapping("/productbyname/{name}")
	public ResponseEntity<SuccessMessage> getProductByNameHandler(@PathVariable("name") String name) {
		return new ResponseEntity<SuccessMessage>(ps.getProductByName(name), HttpStatus.OK);
	}

	@GetMapping("/productbetweenprice/{minprice}/{maxprice}")
	public ResponseEntity<SuccessMessage> getProductByNameHandler(@PathVariable("minprice") Double minPrice,
			@PathVariable("maxprice") Double maxPrice) {
		return new ResponseEntity<SuccessMessage>(ps.getProductBetweenPrice(minPrice, maxPrice), HttpStatus.OK);
	}

	@GetMapping("/allproduct")
	public ResponseEntity<SuccessMessage> getAllProductHandler() {
		return new ResponseEntity<SuccessMessage>(ps.getAllProducts(), HttpStatus.OK);
	}

	@PostMapping("/addproduct/{authkey}/{cat_id}")
	public ResponseEntity<SuccessMessage> addProductHandler(@RequestBody @Valid ProductDto product,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(ps.addProduct(product, authKey), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteproduct/{authkey}/{id}")
	public ResponseEntity<SuccessMessage> addProductHandler(@PathVariable("id") Integer id,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(ps.removeProduct(id, authKey), HttpStatus.OK);
	}

	@GetMapping("/productbyid/{authkey}/{id}")
	public ResponseEntity<SuccessMessage> getProductByIdHandler(@PathVariable("id") Integer id,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(ps.getProductById(id, authKey), HttpStatus.OK);
	}

	@PostMapping("/updateproduct/{authkey}")
	public ResponseEntity<SuccessMessage> updateProductHandler(@RequestBody @Valid ProductDto product,
			@PathVariable("authKey") String authKey) {
		return new ResponseEntity<SuccessMessage>(ps.updateProduct(product, authKey), HttpStatus.CREATED);
	}

	@PutMapping("/updateproductprice/{authkey}/{id}/{price}")
	public ResponseEntity<SuccessMessage> updateProductPriceHandler(@PathVariable("id") Integer id,
			@PathVariable("price") Double price, @PathVariable("authKey") String authKey) {
		return new ResponseEntity<SuccessMessage>(ps.updateProductPrice(id, price, authKey), HttpStatus.CREATED);
	}

	@GetMapping("/productbycategoryid/{cid}")
	public ResponseEntity<SuccessMessage> getProductByCategoryIdHandler(@PathVariable("cid") Integer cid) {
		return new ResponseEntity<SuccessMessage>(ps.getProductsByCategoryId(cid), HttpStatus.OK);
	}

}
