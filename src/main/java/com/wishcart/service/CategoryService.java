package com.wishcart.service;

import com.wishcart.dto.CategoryDto;
import com.wishcart.exception.CategoryException;
import com.wishcart.model.SuccessMessage;

public interface CategoryService {

	public SuccessMessage addCategory(CategoryDto category) throws CategoryException;

	public SuccessMessage removeCategory(Long id) throws CategoryException;

	public SuccessMessage getAllCategories();

}
