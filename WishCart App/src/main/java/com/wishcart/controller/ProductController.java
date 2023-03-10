package com.wishcart.controller;

import java.util.List;

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

import com.wishcart.dto.ProductDto;
import com.wishcart.model.Product;
import com.wishcart.service.ProductService;

@RestController
@RequestMapping("/wishcart")
public class ProductController {

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

	@PostMapping("/addproduct/{authkey}/{cat_id}")
	public ResponseEntity<Product> addProductHandler(@RequestBody @Valid ProductDto product,
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<Product>(ps.addProduct(product, authKey), HttpStatus.CREATED);
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
	public ResponseEntity<Product> updateProductHandler(@RequestBody @Valid ProductDto product,
			@PathVariable("authKey") String authKey) {
		return new ResponseEntity<Product>(ps.updateProduct(product, authKey), HttpStatus.CREATED);
	}

	@PutMapping("updateproductprice/{authkey}/{id}/{price}")
	public ResponseEntity<Product> updateProductPriceHandler(@PathVariable("id") Integer id,
			@PathVariable("price") Double price, @PathVariable("authKey") String authKey) {
		return new ResponseEntity<Product>(ps.updateProductPrice(id, price, authKey), HttpStatus.CREATED);
	}

}
