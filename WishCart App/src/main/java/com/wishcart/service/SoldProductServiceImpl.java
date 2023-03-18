package com.wishcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishcart.exception.AdminException;
import com.wishcart.exception.CustomerException;
import com.wishcart.exception.SoldProductException;
import com.wishcart.model.Admin;
import com.wishcart.model.CurrentUserSession;
import com.wishcart.model.Customer;
import com.wishcart.model.SoldProduct;
import com.wishcart.repository.AdminDao;
import com.wishcart.repository.CurrentUserSessionDao;
import com.wishcart.repository.CustomerDao;
import com.wishcart.repository.SoldProductDao;

@Service
public class SoldProductServiceImpl implements SoldProductService {

	@Autowired
	private SoldProductDao soldproductdao;

	@Autowired
	private CurrentUserSessionDao cusdao;

	@Autowired
	private CustomerDao customerdao;

	@Autowired
	private AdminDao admindao;

	@Override
	public String addToSoldProduct(SoldProduct soldProduct, String authKey) throws SoldProductException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		customerdao.findByEmail(cus.getEmail()).orElseThrow(() -> new CustomerException("Invalid authKey"));

		soldproductdao.save(soldProduct);

		return "updated";

	}

	@Override
	public List<SoldProduct> getAllPurchasedProductByCustomer(String authKey) throws SoldProductException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = customerdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("Invalid authKey"));

		List<SoldProduct> purchasedProducts = soldproductdao.findAllByCustomerOrderByPurchaseDateDesc(customer);

		if (purchasedProducts.size() == 0)
			throw new SoldProductException("You have not purchased any product yet");

		return purchasedProducts;
	}

	@Override
	public List<SoldProduct> getAllSoldProductByAdmin(String authKey) throws SoldProductException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Admin admin = admindao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid authKey"));

		List<SoldProduct> soldProducts = soldproductdao.findAllByAdminOrderByPurchaseDateDesc(admin);

		if (soldProducts.size() == 0)
			throw new SoldProductException("You have not sold any product yet");

		return soldProducts;
	}

}
