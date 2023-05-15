package com.wishcart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.Category;
import com.wishcart.model.SuccessMessage;
import com.wishcart.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService cs;

	@PostMapping("/add")
	public ResponseEntity<SuccessMessage> addCategoryHandler(@RequestBody @Valid Category category) {
		return new ResponseEntity<SuccessMessage>(cs.addCategory(category), HttpStatus.OK);
	}

	@DeleteMapping("/remove?cat_id")
	public ResponseEntity<SuccessMessage> removeategoryHandler(@RequestParam("cat_id") Integer id) {
		return new ResponseEntity<SuccessMessage>(cs.removeCategory(id), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<SuccessMessage> updateCategoryHandler(@RequestBody @Valid Category category) {
		return new ResponseEntity<SuccessMessage>(cs.updateCategory(category), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<SuccessMessage> getAllCategoryHandler() {
		return new ResponseEntity<SuccessMessage>(cs.getAllCategory(), HttpStatus.OK);
	}

}
