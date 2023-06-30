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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SellerController {

	private final ProductService productService;

	@Operation(
			description = "It takes object as a request body",
			summary = "Endpoint for adding a new product"
	)
	@PostMapping("/product/add")
	public ResponseEntity<SuccessMessage> addProductHandler(@RequestBody ProductDto productDto, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.addProduct(productDto, email), HttpStatus.CREATED);
	}

	@Operation(
			description = "It takes product id as a parameter",
			summary = "Endpoint for removing a product"
	)
	@DeleteMapping("/product/remove")
	public ResponseEntity<SuccessMessage> removeProductHandler(@RequestParam("id") Long id, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.removeProduct(id, email), HttpStatus.OK);
	}

	@Operation(
			description = "It takes product id as a parameter",
			summary = "Endpoint for getting a product"
	)
	@GetMapping("/product")
	public ResponseEntity<SuccessMessage> getProductByIdHandler(@RequestParam("id") Long id, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getProductById(id, email), HttpStatus.OK);
	}

	@Operation(
			description = "It takes query as a parameter",
			summary = "Endpoint for getting product list by query"
	)
	@GetMapping("/product/search")
	public ResponseEntity<SuccessMessage> getProductByIdHandler(@RequestParam("q") String name, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getProductByName(name, email), HttpStatus.OK);
	}

	@Operation(
			summary = "Endpoint for getting product"
	)
	@GetMapping("/products/getall")
	public ResponseEntity<SuccessMessage> getAllProductHandler(Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getAllProducts(email), HttpStatus.OK);
	}

	@Operation(
			description = "It takes category id as a parameter and return all products of the seller",
			summary = "Endpoint for getting product list"
	)
	@GetMapping("/products/bycategory")
	public ResponseEntity<SuccessMessage> getAllProductByCategoryHandler(@RequestParam("id") Long id,
			Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getProductsByCategoryId(id, email), HttpStatus.OK);
	}

	@Operation(
			description = "It takes object as a request body",
			summary = "Endpoint for updating a product"
	)
	@PutMapping("/product/update")
	public ResponseEntity<SuccessMessage> updateProductHandler(@RequestBody UpdateProductDto productDto,
			Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.updateProduct(productDto, email), HttpStatus.OK);
	}

	@Operation(
			description = "It takes product id and price as a parameter",
			summary = "Endpoint for updating the price of a product"
	)
	@PutMapping("/product/updateprice")
	public ResponseEntity<SuccessMessage> updateProductPriceHandler(@RequestParam("id") Long id,
			@RequestParam("price") Double price, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.updateProductPrice(id, price, email), HttpStatus.OK);
	}

}
