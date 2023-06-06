package com.wishcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.dto.CategoryDto;
import com.wishcart.model.SuccessMessage;
import com.wishcart.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

	private final CategoryService categoryService;

	@PostMapping("/category/add")
	public ResponseEntity<SuccessMessage> addCategoryHandler(CategoryDto category) {

		return new ResponseEntity<SuccessMessage>(categoryService.addCategory(category), HttpStatus.CREATED);

	}

	@DeleteMapping("/category/delete")
	public ResponseEntity<SuccessMessage> removeCategoryHandler(@RequestParam("id") Long id) {

		return new ResponseEntity<SuccessMessage>(categoryService.removeCategory(id), HttpStatus.OK);

	}

}
