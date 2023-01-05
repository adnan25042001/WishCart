package com.wishcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wishcart.exception.AdminException;
import com.wishcart.exception.ProductException;
import com.wishcart.model.CurrentUserSession;
import com.wishcart.model.Product;
import com.wishcart.repository.AdminDao;
import com.wishcart.repository.CurrentUserSessionDao;
import com.wishcart.repository.ProductDao;

public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao pdao;

	@Autowired
	private AdminDao adao;

	@Autowired
	private CurrentUserSessionDao cusdao;

	@Override
	public Product addProduct(Product product, String authKey) {
		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("User not logged in"));

		adao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid auth Key : " + authKey));

		return pdao.save(product);
	}

	@Override
	public Product removeProduct(Integer id, String authKey) throws ProductException {
		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("User not logged in"));

		adao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid auth Key : " + authKey));

		Product product = pdao.findById(id).orElseThrow(() -> new ProductException("Invalid Product Id : " + id));

		pdao.delete(product);

		return product;
	}

	@Override
	public Product getProductById(Integer id, String authKey) throws ProductException {
		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("User not logged in"));

		adao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid auth Key : " + authKey));

		Product product = pdao.findById(id).orElseThrow(() -> new ProductException("Invalid Product Id : " + id));

		return product;
	}

	@Override
	public List<Product> getProductByName(String name) throws ProductException {
		List<Product> products = pdao.findByProductName(name);
		if (products.isEmpty())
			throw new ProductException("Product not found by name : " + name);
		return products;
	}

	@Override
	public List<Product> getProductBetweenPrice(Double minPrice, Double maxPrice) throws ProductException {
		List<Product> products = pdao.findByPriceBetween(minPrice, maxPrice);
		if (products.isEmpty())
			throw new ProductException("Product not found between : " + minPrice + " and " + maxPrice);
		return products;
	}

	@Override
	public List<Product> getAllProducts() throws ProductException {
		List<Product> products = pdao.findAll();
		if (products.isEmpty())
			throw new ProductException("Product not found by");
		return products;
	}

	@Override
	public Product updateProduct(Product product, String authKey) throws ProductException {
		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("User not logged in"));

		adao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid auth Key : " + authKey));

		pdao.findById(product.getProductId())
				.orElseThrow(() -> new ProductException("Invalid product Id : " + product.getProductId()));

		return pdao.save(product);
	}

	@Override
	public Product updateProductPrice(Integer id, Double price, String authKey) throws ProductException {
		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("User not logged in"));

		adao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid auth Key : " + authKey));

		Product prod = pdao.findById(id).orElseThrow(() -> new ProductException("Invalid product Id : " + id));

		prod.setPrice(price);

		return pdao.save(prod);
	}

}
