package com.wishcart.service;

import com.wishcart.dto.ProductDto;
import com.wishcart.exception.CategoryException;
import com.wishcart.exception.ProductException;
import com.wishcart.model.SuccessMessage;

public interface ProductService {

	public SuccessMessage addProduct(ProductDto product, String authKey);

	public SuccessMessage removeProduct(Integer id, String authKey) throws ProductException;

	public SuccessMessage getProductById(Integer id, String authKey) throws ProductException;

	public SuccessMessage getProductByName(String name) throws ProductException;

	public SuccessMessage getProductBetweenPrice(Double minPrice, Double maxPrice) throws ProductException;

	public SuccessMessage getAllProducts() throws ProductException;

	public SuccessMessage getProductsByCategoryId(Integer cid) throws ProductException, CategoryException;

	public SuccessMessage updateProduct(ProductDto product, String authKey) throws ProductException;

	public SuccessMessage updateProductPrice(Integer id, Double price, String authKey) throws ProductException;

}
