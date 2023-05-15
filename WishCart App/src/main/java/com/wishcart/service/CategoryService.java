package com.wishcart.service;

import com.wishcart.exception.CategoryException;
import com.wishcart.model.Category;
import com.wishcart.model.SuccessMessage;

public interface CategoryService {

	public SuccessMessage addCategory(Category category);

	public SuccessMessage removeCategory(Integer id) throws CategoryException;

	public SuccessMessage updateCategory(Category category) throws CategoryException;

	public SuccessMessage getAllCategory() throws CategoryException;

}
