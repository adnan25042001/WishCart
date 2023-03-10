package com.wishcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wishcart.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	public List<Product> findByProductName(String name);

	public List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

	public List<Product> findByPrice(Double price);

}
