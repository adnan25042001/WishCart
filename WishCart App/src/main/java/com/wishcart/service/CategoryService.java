package com.wishcart.service;

import java.util.List;

import com.wishcart.exception.CategoryException;
import com.wishcart.model.Category;

public interface CategoryService {

	public Category addCategory(Category category);

	public Category removeCategory(Integer id) throws CategoryException;

	public Category updateCategory(Category category) throws CategoryException;

	public List<Category> getAllCategory() throws CategoryException;

}
