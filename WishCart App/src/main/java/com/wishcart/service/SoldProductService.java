package com.wishcart.service;

import java.util.List;

import com.wishcart.exception.SoldProductException;
import com.wishcart.model.SoldProduct;

public interface SoldProductService {

	public String addToSoldProduct(SoldProduct soldProduct, String authKey) throws SoldProductException;

	public List<SoldProduct> getAllPurchasedProductByCustomer(String authKey) throws SoldProductException;

	public List<SoldProduct> getAllSoldProductByAdmin(String authKey) throws SoldProductException;

}
