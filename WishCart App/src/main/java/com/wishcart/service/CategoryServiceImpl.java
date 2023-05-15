package com.wishcart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishcart.exception.CategoryException;
import com.wishcart.model.Category;
import com.wishcart.model.SuccessMessage;
import com.wishcart.repository.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao cdao;

	@Override
	public SuccessMessage addCategory(Category category) {

		Optional<Category> opt = cdao.findByCategoryName(category.getCategoryName());

		if (opt.isPresent())
			throw new CategoryException("This category is already present");

		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<Category> list = new ArrayList<>();
		list.add(cdao.save(category));
		successMessage.setResult(list);

		return successMessage;
	}

	@Override
	public SuccessMessage removeCategory(Integer id) throws CategoryException {
		Category category = cdao.findById(id).orElseThrow(() -> new CategoryException("Invalid Category Id : " + id));
		cdao.delete(category);

		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<Category> list = new ArrayList<>();
		list.add(category);
		successMessage.setResult(list);

		return successMessage;
	}

	@Override
	public SuccessMessage updateCategory(Category category) throws CategoryException {
		cdao.findById(category.getId())
				.orElseThrow(() -> new CategoryException("Invalid Category Id : " + category.getId()));

		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<Category> list = new ArrayList<>();
		list.add(cdao.save(category));
		successMessage.setResult(list);

		return successMessage;

	}

	@Override
	public SuccessMessage getAllCategory() throws CategoryException {
		List<Category> categories = cdao.findAll();
		if (categories.isEmpty()) {
			throw new CategoryException("Category not found");
		}

		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(categories.size());
		successMessage.setResult(categories);

		return successMessage;

	}

}
