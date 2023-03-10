package com.wishcart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishcart.dto.ProductDto;
import com.wishcart.exception.AdminException;
import com.wishcart.exception.CategoryException;
import com.wishcart.exception.ProductException;
import com.wishcart.model.Category;
import com.wishcart.model.CurrentUserSession;
import com.wishcart.model.Product;
import com.wishcart.repository.AdminDao;
import com.wishcart.repository.CategoryDao;
import com.wishcart.repository.CurrentUserSessionDao;
import com.wishcart.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao pdao;

	@Autowired
	private CategoryDao cdao;

	@Autowired
	private AdminDao adao;

	@Autowired
	private CurrentUserSessionDao cusdao;

	@Override
	public Product addProduct(ProductDto product, String authKey) {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("User not logged in"));

		adao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid auth Key : " + authKey));

		Category category = cdao.findById(product.getCategory_cid()).orElseThrow(
				() -> new CategoryException("Category not found with cat_id : " + product.getCategory_cid()));

		Product prod = new Product();

		prod.setCategory(category);
		prod.setDescription(product.getDescription());
		prod.setImage1(product.getImage1());
		prod.setImage2(product.getImage2());
		prod.setImage3(product.getImage3());
		prod.setPrice(product.getPrice());
		prod.setProductName(product.getProductName());
		prod.setQuantity(product.getQuantity());

		return pdao.save(prod);

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
	public Product updateProduct(ProductDto product, String authKey) throws ProductException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("User not logged in"));

		adao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid auth Key : " + authKey));

		Product prod = pdao.findById(product.getProductId())
				.orElseThrow(() -> new ProductException("Invalid product Id : " + product.getProductId()));

		prod.setDescription(product.getDescription());
		prod.setImage1(product.getImage1());
		prod.setImage2(product.getImage2());
		prod.setImage3(product.getImage3());
		prod.setPrice(product.getPrice());
		prod.setProductName(product.getProductName());
		prod.setQuantity(product.getQuantity());

		return pdao.save(prod);

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

	@Override
	public List<ProductDto> getProductsByCategoryId(Integer cid) throws ProductException, CategoryException {

		cdao.findById(cid).orElseThrow(() -> new CategoryException("Invalid Category Id"));

		List<Product> products = pdao.findAll();

		List<ProductDto> productList = new ArrayList<>();

		for (Product p : products) {
			if (p.getCategory().getCid() == cid) {
				ProductDto prod = new ProductDto();
				prod.setCategory_cid(cid);
				prod.setDescription(p.getDescription());
				prod.setImage1(p.getImage1());
				prod.setImage2(p.getImage2());
				prod.setImage3(p.getImage3());
				prod.setPrice(p.getPrice());
				prod.setProductId(p.getProductId());
				prod.setProductName(p.getProductName());
				prod.setQuantity(p.getQuantity());

				productList.add(prod);
			}
		}

		if (productList.size() == 0)
			throw new ProductException("Product not found");

		return productList;

	}

}
