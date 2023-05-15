package com.wishcart.service;

import com.wishcart.exception.SoldProductException;
import com.wishcart.model.SoldProduct;
import com.wishcart.model.SuccessMessage;

public interface SoldProductService {

	public SuccessMessage addToSoldProduct(SoldProduct soldProduct, String authKey) throws SoldProductException;

	public SuccessMessage getAllPurchasedProductByCustomer(String authKey) throws SoldProductException;

	public SuccessMessage getAllSoldProductByAdmin(String authKey) throws SoldProductException;

}
