package com.wishcart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wishcart.dto.ProductDto;
import com.wishcart.dto.UpdateProductDto;
import com.wishcart.exception.CategoryException;
import com.wishcart.exception.ProductException;
import com.wishcart.exception.UserException;
import com.wishcart.model.Category;
import com.wishcart.model.Image;
import com.wishcart.model.Product;
import com.wishcart.model.SuccessMessage;
import com.wishcart.model.User;
import com.wishcart.repository.CategoryRepository;
import com.wishcart.repository.ImageRepository;
import com.wishcart.repository.ProductRepository;
import com.wishcart.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ImageRepository imageRepository;
	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;

	@Override
	public SuccessMessage addProduct(ProductDto product, String email) {

		var seller = getUser(email);

		var newProduct = Product.builder().productName(product.getProductName()).description(product.getDescription())
				.price(product.getPrice()).seller(seller)
				.images(product.getImages().stream().map((i) -> imageRepository.save(Image.builder().url(i).build()))
						.collect(Collectors.toSet()))
				.categories(product.getCategoryId().stream()
						.map((id) -> categoryRepository.findById(id)
								.orElseThrow(() -> new CategoryException("Invalid category id: " + id)))
						.collect(Collectors.toList()))
				.build();

		return SuccessMessage.builder().data(List.of(productRepository.save(newProduct))).size(1).success(true).build();

	}

	@Override
	public SuccessMessage removeProduct(Long id, String email) throws ProductException {

		var seller = getUser(email);

		var product = productRepository.findById(id)
				.orElseThrow(() -> new ProductException("Invalid product id: " + id));

		if (!product.getSeller().equals(seller))
			throw new ProductException("Invalid product id: " + id);

		productRepository.delete(product);

		return SuccessMessage.builder().data(List.of(product)).size(1).success(true).build();

	}

	@Override
	public SuccessMessage getProductById(Long id, String email) throws ProductException {

		var seller = getUser(email);

		var product = productRepository.findById(id)
				.orElseThrow(() -> new ProductException("Invalid product id: " + id));

		if (seller.getRole().name().equalsIgnoreCase("seller")) {
			if (seller.getId() == product.getSeller().getId()) {
				return SuccessMessage.builder().data(List.of(product)).size(1).success(true).build();
			} else {
				throw new ProductException("Invalid product id: " + id);
			}
		}

		return SuccessMessage.builder().data(List.of(product)).size(1).success(true).build();

	}

	@Override
	public SuccessMessage getProductByName(String name, String email) throws ProductException {

		var products = productRepository.searchProduct(name);

		if (products.isEmpty())
			throw new ProductException("Product not found by name :" + name);

		var seller = getUser(email);

		if (seller.getRole().name().equalsIgnoreCase("seller")) {

			List<Product> sellerProducts = new ArrayList<>();
			for (Product p : products) {
				if (p.getSeller().getId() == seller.getId()) {
					sellerProducts.add(p);
				}
			}
			if (sellerProducts.size() == 0)
				throw new ProductException("Product not found by name: " + name);

			return SuccessMessage.builder().success(true).data(sellerProducts).size(sellerProducts.size()).build();

		}

		return SuccessMessage.builder().success(true).data(products).size(products.size()).build();

	}

	@Override
	public SuccessMessage getProductBetweenPrice(Double minPrice, Double maxPrice) throws ProductException {

		var products = productRepository.findByPriceBetween(minPrice, maxPrice);

		if (products.isEmpty())
			throw new ProductException("Product not found between this price range");

		return SuccessMessage.builder().success(true).data(products).size(products.size()).build();

	}

	@Override
	public SuccessMessage getAllProducts(String email) throws ProductException {

		var products = productRepository.findAll();

		if (products.isEmpty())
			throw new ProductException("Products not found");

		var seller = getUser(email);

		if (seller.getRole().name().equalsIgnoreCase("seller")) {

			List<Product> sellerProducts = new ArrayList<>();
			for (Product p : products) {
				if (p.getSeller().getId() == seller.getId()) {
					sellerProducts.add(p);
				}
			}
			if (sellerProducts.size() == 0)
				throw new ProductException("Product not found");

			return SuccessMessage.builder().success(true).data(sellerProducts).size(sellerProducts.size()).build();

		}

		return SuccessMessage.builder().success(true).data(products).size(products.size()).build();

	}

	@Override
	public SuccessMessage getProductsByCategoryId(Long cid, String email) throws ProductException, CategoryException {

		categoryRepository.findById(cid).orElseThrow(() -> new CategoryException("Wrong category id: " + cid));

		List<Product> products = productRepository.findAll();

		if (products.size() == 0)
			throw new ProductException("Product not found with category id: " + cid);

		var seller = getUser(email);

		if (seller.getRole().name().equalsIgnoreCase("seller")) {

			List<Product> sellerProducts = new ArrayList<>();
			for (Product p : products) {
				if (p.getSeller().getId() == seller.getId()) {
					for (Category c : p.getCategories()) {
						if (c.getId() == cid) {
							sellerProducts.add(p);
							break;
						}
					}
				}
			}

			if (sellerProducts.size() == 0)
				throw new ProductException("Product not found with category id: " + cid);

			return SuccessMessage.builder().success(true).data(sellerProducts).size(sellerProducts.size()).build();

		}

		List<Product> productListByCategory = new ArrayList<>();

		for (Product p : products) {
			for (Category c : p.getCategories()) {
				if (c.getId() == cid) {
					productListByCategory.add(p);
					break;
				}
			}
		}

		if (productListByCategory.size() == 0)
			throw new ProductException("Product not found with category id: " + cid);

		return SuccessMessage.builder().success(true).data(productListByCategory).size(productListByCategory.size())
				.build();

	}

	@Override
	public SuccessMessage updateProduct(UpdateProductDto product, String email) throws ProductException {

		var seller = getUser(email);

		var prod = productRepository.findById(product.getId())
				.orElseThrow(() -> new ProductException("Invalid product id: " + product.getId()));

		if (seller.getId() != prod.getSeller().getId())
			throw new UserException("Unauthorized");

		if (!product.getProductName().equals(null)) {
			prod.setProductName(product.getProductName());
		}
		if (!product.getCategoryId().equals(null)) {
			for (Long cid : product.getCategoryId()) {
				var category = categoryRepository.findById(cid)
						.orElseThrow(() -> new CategoryException("Invalid category id: " + cid));
				prod.getCategories().add(category);
			}
		}
		if (!product.getDescription().equals(null)) {
			prod.setDescription(product.getDescription());
		}
		if (!product.getPrice().equals(null)) {
			prod.setPrice(product.getPrice());
		}

		return SuccessMessage.builder().success(true).data(List.of(productRepository.save(prod))).size(1).build();

	}

	@Override
	public SuccessMessage updateProductPrice(Long id, Double price, String email) throws ProductException {

		var seller = getUser(email);

		var product = productRepository.findById(id)
				.orElseThrow(() -> new ProductException("Invalid product id: " + id));

		if (seller.getId() != product.getSeller().getId())
			throw new UserException("Unauthorized");

		product.setPrice(price);

		return SuccessMessage.builder().success(true).data(List.of(productRepository.save(product))).size(1).build();

	}

	private User getUser(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
	}

}
