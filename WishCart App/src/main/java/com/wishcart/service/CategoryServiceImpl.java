package com.wishcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishcart.exception.CategoryException;
import com.wishcart.model.Category;
import com.wishcart.repository.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao cdao;

	@Override
	public Category addCategory(Category category) {
		return cdao.save(category);
	}

	@Override
	public Category removeCategory(Integer id) throws CategoryException {
		Category category = cdao.findById(id).orElseThrow(() -> new CategoryException("Invalid Category Id : " + id));
		cdao.delete(category);
		return category;
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {
		cdao.findById(category.getCid())
				.orElseThrow(() -> new CategoryException("Invalid Category Id : " + category.getCid()));
		return cdao.save(category);
	}

	@Override
	public List<Category> getAllCategory() throws CategoryException {
		List<Category> categories = cdao.findAll();
		if (categories.isEmpty()) {
			throw new CategoryException("Category not found");
		}
		return categories;
	}

}
