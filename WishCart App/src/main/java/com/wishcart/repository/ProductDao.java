package com.wishcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

	public List<Product> findByProductName(String name);

	public List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
	
	public List<Product> findByPrice(Double price);

}
