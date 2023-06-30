package com.wishcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.dto.CategoryDto;
import com.wishcart.model.SuccessMessage;
import com.wishcart.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

	private final CategoryService categoryService;

	@Operation(
			description = "It takes object as a request body",
			summary = "Endpoint for adding a new category"
	)
	@PostMapping("/category/add")
	public ResponseEntity<SuccessMessage> addCategoryHandler(@RequestBody CategoryDto category) {

		return new ResponseEntity<SuccessMessage>(categoryService.addCategory(category), HttpStatus.CREATED);

	}

	@Operation(
			description = "It takes category id as a parameter",
			summary = "Endpoint for removing the category"
	)
	@DeleteMapping("/category/delete")
	public ResponseEntity<SuccessMessage> removeCategoryHandler(@RequestParam("id") Long id) {

		return new ResponseEntity<SuccessMessage>(categoryService.removeCategory(id), HttpStatus.OK);

	}

}
