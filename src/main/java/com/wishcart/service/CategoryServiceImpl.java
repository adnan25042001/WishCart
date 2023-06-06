package com.wishcart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wishcart.dto.CategoryDto;
import com.wishcart.exception.CategoryException;
import com.wishcart.model.Category;
import com.wishcart.model.SuccessMessage;
import com.wishcart.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public SuccessMessage addCategory(CategoryDto category) throws CategoryException {

		Optional<Category> opt = categoryRepository.findByCategoryName(category.getCategoryName());

		if (opt.isPresent())
			throw new CategoryException("Category already exists with name: " + category.getCategoryName());

		Category newCategory = Category.builder().categoryName(category.getCategoryName())
				.description(category.getDescription()).img(category.getImg()).build();

		Category savedCategory = categoryRepository.save(newCategory);

		return SuccessMessage.builder().data(List.of(savedCategory)).size(1).success(true).build();

	}

	@Override
	public SuccessMessage removeCategory(Long id) throws CategoryException {

		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryException("Invalid category id"));

		categoryRepository.delete(category);

		return SuccessMessage.builder().data(List.of(category)).size(1).success(true).build();

	}

	@Override
	public SuccessMessage getAllCategories() {

		List<Category> categories = categoryRepository.findAll();

		return SuccessMessage.builder().data(categories).size(categories.size()).success(true).build();

	}

}
