package com.wishcart.service;

import com.wishcart.dto.ProductDto;
import com.wishcart.dto.UpdateProductDto;
import com.wishcart.exception.CategoryException;
import com.wishcart.exception.ProductException;
import com.wishcart.model.SuccessMessage;

public interface ProductService {

	public SuccessMessage addProduct(ProductDto product, String email);

	public SuccessMessage removeProduct(Long id, String email) throws ProductException;

	public SuccessMessage getProductById(Long id, String email) throws ProductException;

	public SuccessMessage getProductByName(String name, String email) throws ProductException;

	public SuccessMessage getProductBetweenPrice(Double minPrice, Double maxPrice) throws ProductException;

	public SuccessMessage getAllProducts(String email) throws ProductException;

	public SuccessMessage getProductsByCategoryId(Long cid, String email) throws ProductException, CategoryException;

	public SuccessMessage updateProduct(UpdateProductDto product, String email) throws ProductException;

	public SuccessMessage updateProductPrice(Long id, Double price, String email) throws ProductException;

}
