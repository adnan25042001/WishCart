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
import com.wishcart.model.Role;
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
	public SuccessMessage addProduct(ProductDto product) {

		var newProduct = Product.builder().productName(product.getProductName()).description(product.getDescription())
				.price(product.getPrice())
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
	public SuccessMessage removeProduct(Long id) throws ProductException {

		var product = productRepository.findById(id)
				.orElseThrow(() -> new ProductException("Invalid product id: " + id));

		productRepository.delete(product);

		return SuccessMessage.builder().data(List.of(product)).size(1).success(true).build();

	}

	@Override
	public SuccessMessage getProductById(Long id) throws ProductException {

		var product = productRepository.findById(id)
				.orElseThrow(() -> new ProductException("Invalid product id: " + id));

		return SuccessMessage.builder().data(List.of(product)).size(1).success(true).build();

	}

	@Override
	public SuccessMessage getProductById(Long id, Role role) throws ProductException {

		var product = productRepository.findById(id)
				.orElseThrow(() -> new ProductException("Invalid product id: " + id));

		if ("seller".equalsIgnoreCase(role.name()) && (product.getSeller().getRole().name()).equals(role.name())) {

			return SuccessMessage.builder().data(List.of(product)).size(1).success(true).build();

		}

		throw new ProductException("Invalid product id: " + id);

	}

	@Override
	public SuccessMessage getProductByName(String name) throws ProductException {

		var products = productRepository.searchProduct(name);

		if (products.isEmpty())
			throw new ProductException("Product not found by name : " + name);

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
	public SuccessMessage getAllProducts() throws ProductException {

		var products = productRepository.findAll();

		if (products.isEmpty())
			throw new ProductException("Products not found");

		return SuccessMessage.builder().success(true).data(products).size(products.size()).build();

	}

	@Override
	public SuccessMessage getProductsByCategoryId(Long cid) throws ProductException, CategoryException {

		var products = productRepository.findAll();

		var productList = new ArrayList<>();

		for (Product p : products) {
			for (Category c : p.getCategories()) {
				if (c.getId() == cid) {
					productList.add(p);
				}
			}
		}

		if (productList.size() == 0) {
			throw new ProductException("Product not found with category id: " + cid);
		}

		return SuccessMessage.builder().success(true).data(productList).size(productList.size()).build();

	}

	@Override
	public SuccessMessage updateProduct(UpdateProductDto product) throws ProductException {

		var prod = productRepository.findById(product.getId())
				.orElseThrow(() -> new ProductException("Invalid product id: " + product.getId()));

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
	public SuccessMessage updateProductPrice(Long id, Double price) throws ProductException {

		var product = productRepository.findById(id)
				.orElseThrow(() -> new ProductException("Invalid product id: " + id));

		product.setPrice(price);

		return SuccessMessage.builder().success(true).data(List.of(productRepository.save(product))).size(1).build();

	}

	@Override
	public SuccessMessage getProductsBySellerId(Long sid) throws ProductException {

		User seller = userRepository.findById(sid).orElseThrow(() -> new UserException("Invalid seller id: " + sid));

		var products = productRepository.findBySeller(seller);

		if (products.size() == 0) {
			throw new ProductException("Product not found");
		}

		return SuccessMessage.builder().success(true).size(1).data(List.of(products)).build();

	}

}
