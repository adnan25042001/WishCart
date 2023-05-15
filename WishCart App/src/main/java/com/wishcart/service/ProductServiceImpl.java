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
import com.wishcart.model.SuccessMessage;
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
	public SuccessMessage addProduct(ProductDto product, String authKey) {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("User not logged in"));

		adao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid auth Key : " + authKey));

		Category category = cdao.findById(product.getCategory_id()).orElseThrow(
				() -> new CategoryException("Category not found with cat_id : " + product.getCategory_id()));

		Product prod = new Product();

		prod.setCategory(category);
		prod.setDescription(product.getDescription());
		prod.setImage1(product.getImage1());
		prod.setImage2(product.getImage2());
		prod.setImage3(product.getImage3());
		prod.setPrice(product.getPrice());
		prod.setProductName(product.getProductName());
		prod.setQuantity(product.getQuantity());
		
		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<Product> list = new ArrayList<>();
		list.add(pdao.save(prod));
		successMessage.setResult(list);

		return successMessage;

	}

	@Override
	public SuccessMessage removeProduct(Integer id, String authKey) throws ProductException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("User not logged in"));

		adao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid auth Key : " + authKey));

		Product product = pdao.findById(id).orElseThrow(() -> new ProductException("Invalid Product Id : " + id));

		pdao.delete(product);
		
		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<Product> list = new ArrayList<>();
		list.add(product);
		successMessage.setResult(list);

		return successMessage;

	}

	@Override
	public SuccessMessage getProductById(Integer id, String authKey) throws ProductException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("User not logged in"));

		adao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid auth Key : " + authKey));

		Product product = pdao.findById(id).orElseThrow(() -> new ProductException("Invalid Product Id : " + id));

		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<Product> list = new ArrayList<>();
		list.add(product);
		successMessage.setResult(list);

		return successMessage;

	}

	@Override
	public SuccessMessage getProductByName(String name) throws ProductException {

		List<Product> products = pdao.searchProduct(name);

		if (products.isEmpty())
			throw new ProductException("Product not found by name : " + name);

		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(products.size());
		successMessage.setResult(products);

		return successMessage;

	}

	@Override
	public SuccessMessage getProductBetweenPrice(Double minPrice, Double maxPrice) throws ProductException {

		List<Product> products = pdao.findByPriceBetween(minPrice, maxPrice);
		if (products.isEmpty())
			throw new ProductException("Product not found between : " + minPrice + " and " + maxPrice);
		
		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(products.size());
		successMessage.setResult(products);

		return successMessage;

	}

	@Override
	public SuccessMessage getAllProducts() throws ProductException {

		List<Product> products = pdao.findAll();

		if (products.isEmpty())
			throw new ProductException("Product not found");
		
		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(products.size());
		successMessage.setResult(products);

		return successMessage;

	}

	@Override
	public SuccessMessage updateProduct(ProductDto product, String authKey) throws ProductException {

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
		
		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<Product> list = new ArrayList<>();
		list.add(pdao.save(prod));
		successMessage.setResult(list);

		return successMessage;

	}

	@Override
	public SuccessMessage updateProductPrice(Integer id, Double price, String authKey) throws ProductException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new AdminException("User not logged in"));

		adao.findByEmail(cus.getEmail()).orElseThrow(() -> new AdminException("Invalid auth Key : " + authKey));

		Product prod = pdao.findById(id).orElseThrow(() -> new ProductException("Invalid product Id : " + id));

		prod.setPrice(price);
		
		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<Product> list = new ArrayList<>();
		list.add(pdao.save(prod));
		successMessage.setResult(list);

		return successMessage;

	}

	@Override
	public SuccessMessage getProductsByCategoryId(Integer cid) throws ProductException, CategoryException {

		cdao.findById(cid).orElseThrow(() -> new CategoryException("Invalid Category Id"));

		List<Product> products = pdao.findAll();

		List<ProductDto> productList = new ArrayList<>();

		for (Product p : products) {
			if (p.getCategory().getId() == cid) {
				ProductDto prod = new ProductDto();
				prod.setCategory_id(cid);
				prod.setDescription(p.getDescription());
				prod.setImage1(p.getImage1());
				prod.setImage2(p.getImage2());
				prod.setImage3(p.getImage3());
				prod.setPrice(p.getPrice());
				prod.setProductId(p.getId());
				prod.setProductName(p.getProductName());
				prod.setQuantity(p.getQuantity());

				productList.add(prod);
			}
		}

		if (productList.size() == 0)
			throw new ProductException("Product not found");
		
		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setResult(productList);
		successMessage.setTotalResult(productList.size());

		return successMessage;

	}

}
