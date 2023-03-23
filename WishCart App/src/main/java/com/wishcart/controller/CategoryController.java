package com.wishcart.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.Category;
import com.wishcart.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService cs;

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/add")
	public ResponseEntity<Category> addCategoryHandler(@RequestBody @Valid Category category) {
		return new ResponseEntity<Category>(cs.addCategory(category), HttpStatus.OK);
	}

	@DeleteMapping("/remove?cat_id")
	public ResponseEntity<Category> removeategoryHandler(@RequestParam("cat_id") Integer id) {
		return new ResponseEntity<Category>(cs.removeCategory(id), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Category> updateCategoryHandler(@RequestBody @Valid Category category) {
		return new ResponseEntity<Category>(cs.updateCategory(category), HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/all")
	public ResponseEntity<List<Category>> getAllCategoryHandler() {
		return new ResponseEntity<List<Category>>(cs.getAllCategory(), HttpStatus.OK);
	}

}
