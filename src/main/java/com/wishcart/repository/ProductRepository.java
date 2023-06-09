package com.wishcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wishcart.model.Category;
import com.wishcart.model.Product;
import com.wishcart.model.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p WHERE p.productName LIKE CONCAT('%', ?1, '%') OR p.description LIKE CONCAT('%', ?1, '%')")
	List<Product> searchProduct(String name);

	List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

	List<Product> findByPrice(Double price);
	
	List<Product> findBySeller(User seller);
	
	List<Product> findByCategories(List<Category> categories);

}
