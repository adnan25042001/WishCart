package com.wishcart.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.dto.ProductDto;
import com.wishcart.dto.UpdateProductDto;
import com.wishcart.model.SuccessMessage;
import com.wishcart.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
public class SellerController {

	private final ProductService productService;

	@PostMapping("/product/add")
	public ResponseEntity<SuccessMessage> addProductHandler(@RequestBody ProductDto productDto, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.addProduct(productDto, email), HttpStatus.CREATED);
	}

	@DeleteMapping("/product/remove")
	public ResponseEntity<SuccessMessage> removeProductHandler(@RequestParam("id") Long id, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.removeProduct(id, email), HttpStatus.OK);
	}

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

	@GetMapping("/products/bycategory")
	public ResponseEntity<SuccessMessage> getAllProductByCategoryHandler(@RequestParam("id") Long id,
			Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getProductsByCategoryId(id, email), HttpStatus.OK);
	}

	@PutMapping("/product/update")
	public ResponseEntity<SuccessMessage> updateProductHandler(@RequestBody UpdateProductDto productDto,
			Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.updateProduct(productDto, email), HttpStatus.OK);
	}

	@PutMapping("/product/updateprice")
	public ResponseEntity<SuccessMessage> updateProductPriceHandler(@RequestParam("id") Long id,
			@RequestParam("price") Double price, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.updateProductPrice(id, price, email), HttpStatus.OK);
	}

}
