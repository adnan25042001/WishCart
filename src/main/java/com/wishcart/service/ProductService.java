package com.wishcart.service;

import com.wishcart.dto.ProductDto;
import com.wishcart.dto.UpdateProductDto;
import com.wishcart.exception.CategoryException;
import com.wishcart.exception.ProductException;
import com.wishcart.model.Role;
import com.wishcart.model.SuccessMessage;

public interface ProductService {

	public SuccessMessage addProduct(ProductDto product);

	public SuccessMessage removeProduct(Long id) throws ProductException;

	public SuccessMessage getProductById(Long id) throws ProductException;

	public SuccessMessage getProductById(Long id, Role role) throws ProductException;

	public SuccessMessage getProductByName(String name) throws ProductException;

	public SuccessMessage getProductBetweenPrice(Double minPrice, Double maxPrice) throws ProductException;

	public SuccessMessage getAllProducts() throws ProductException;

	public SuccessMessage getProductsByCategoryId(Long cid) throws ProductException, CategoryException;

	public SuccessMessage updateProduct(UpdateProductDto product) throws ProductException;

	public SuccessMessage updateProductPrice(Long id, Double price) throws ProductException;

	public SuccessMessage getProductsBySellerId(Long sid) throws ProductException;

}
