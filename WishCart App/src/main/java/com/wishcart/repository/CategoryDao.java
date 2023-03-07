package com.wishcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wishcart.model.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
	
	public Optional<Category> findByCategoryName(String cname);

}
