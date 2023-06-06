package com.wishcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.SuccessMessage;
import com.wishcart.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/v1")
@RequiredArgsConstructor
public class CommonController {

	private final CategoryService categoryService;

	@GetMapping("/categories/getall")
	public ResponseEntity<SuccessMessage> getAllCategoriesHandler() {

		return new ResponseEntity<SuccessMessage>(categoryService.getAllCategories(), HttpStatus.OK);

	}

}
