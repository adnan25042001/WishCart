package com.wishcart.service;

import java.util.List;

import com.wishcart.dto.ProductDto;
import com.wishcart.exception.ProductException;
import com.wishcart.model.Product;

public interface ProductService {

	public Product addProduct(ProductDto product, String authKey);

	public Product removeProduct(Integer id, String authKey) throws ProductException;

	public Product getProductById(Integer id, String authKey) throws ProductException;

	public List<Product> getProductByName(String name) throws ProductException;

	public List<Product> getProductBetweenPrice(Double minPrice, Double maxPrice) throws ProductException;

	public List<Product> getAllProducts() throws ProductException;

	public Product updateProduct(ProductDto product, String authKey) throws ProductException;

	public Product updateProductPrice(Integer id, Double price, String authKey) throws ProductException;

}
