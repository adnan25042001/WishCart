package com.wishcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.SoldProduct;
import com.wishcart.service.SoldProductService;

@RestController
public class SoldProductController {

	@Autowired
	private SoldProductService soldProductService;

	@GetMapping("/getallpurchasedproduct/{authkey}")
	public ResponseEntity<List<SoldProduct>> getAllPurchasedProductByCustomerHandler(
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<List<SoldProduct>>(soldProductService.getAllPurchasedProductByCustomer(authKey),
				HttpStatus.OK);
	}

	@GetMapping("/getallsoldproduct/{authkey}")
	public ResponseEntity<List<SoldProduct>> getAllSoldProductByAdminHandler(@PathVariable("authkey") String authKey) {
		return new ResponseEntity<List<SoldProduct>>(soldProductService.getAllSoldProductByAdmin(authKey),
				HttpStatus.OK);
	}

}
