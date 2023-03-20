package com.wishcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.wishcart.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.productName LIKE CONCAT('%', ?1, '%') OR p.description LIKE CONCAT('%', ?1, '%')")
	public List<Product> searchProduct(String name);

	public List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

	public List<Product> findByPrice(Double price);

}
