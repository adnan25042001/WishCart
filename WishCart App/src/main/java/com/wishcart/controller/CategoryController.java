package com.wishcart.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.Category;
import com.wishcart.service.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/wishcart")
public class CategoryController {

	@Autowired
	private CategoryService cs;

	@PostMapping("/addcategory")
	public ResponseEntity<Category> addCategoryHandler(@RequestBody @Valid Category category) {
		return new ResponseEntity<Category>(cs.addCategory(category), HttpStatus.CREATED);
	}

	@DeleteMapping("/removecategory?cat_id")
	public ResponseEntity<Category> removeategoryHandler(@RequestParam("cat_id") Integer id) {
		return new ResponseEntity<Category>(cs.removeCategory(id), HttpStatus.CREATED);
	}

	@PostMapping("/updatecategory")
	public ResponseEntity<Category> updateCategoryHandler(@RequestBody @Valid Category category) {
		return new ResponseEntity<Category>(cs.updateCategory(category), HttpStatus.CREATED);
	}

	@GetMapping("/allCategory")
	public ResponseEntity<List<Category>> getAllCategoryHandler() {
		return new ResponseEntity<List<Category>>(cs.getAllCategory(), HttpStatus.OK);
	}

}
