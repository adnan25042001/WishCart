package com.wishcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.SuccessMessage;
import com.wishcart.service.SoldProductService;

@RestController
public class SoldProductController {

	@Autowired
	private SoldProductService soldProductService;

	@GetMapping("/getallpurchasedproduct/{authkey}")
	public ResponseEntity<SuccessMessage> getAllPurchasedProductByCustomerHandler(
			@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(soldProductService.getAllPurchasedProductByCustomer(authKey),
				HttpStatus.OK);
	}

	@GetMapping("/getallsoldproduct/{authkey}")
	public ResponseEntity<SuccessMessage> getAllSoldProductByAdminHandler(@PathVariable("authkey") String authKey) {
		return new ResponseEntity<SuccessMessage>(soldProductService.getAllSoldProductByAdmin(authKey), HttpStatus.OK);
	}

}
