package com.wishcart.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishcart.dto.CartDto;
import com.wishcart.dto.CartItemDto;
import com.wishcart.exception.CartException;
import com.wishcart.exception.CustomerException;
import com.wishcart.exception.ProductException;
import com.wishcart.exception.WishlistException;
import com.wishcart.model.Cart;
import com.wishcart.model.CurrentUserSession;
import com.wishcart.model.Customer;
import com.wishcart.model.Product;
import com.wishcart.repository.CartDao;
import com.wishcart.repository.CurrentUserSessionDao;
import com.wishcart.repository.CustomerDao;
import com.wishcart.repository.ProductDao;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartdao;

	@Autowired
	private ProductDao pdao;

	@Autowired
	private CustomerDao cdao;

	@Autowired
	private CurrentUserSessionDao cusdao;

	@Override
	public String addToCart(Integer productId, String authKey) throws ProductException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = cdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("Invalid authKey"));

		Product product = pdao.findById(productId)
				.orElseThrow(() -> new ProductException("Product not found with id : " + productId));

		Optional<Cart> opt = cartdao.findByCustomer_idAndProduct_id(customer.getId(), productId);

		if (opt.isPresent()) {
			throw new WishlistException("Product already added to cart");
		}

		Cart cart = new Cart();

		cart.setCreatedDate(new Date());
		cart.setCustomer(customer);
		cart.setProduct(product);
		cart.setQuantity(1);

		cartdao.save(cart);

		return "product added to cart";

	}

	@Override
	public String updateProductQuantity(Integer productId, Integer quantity, String authKey)
			throws ProductException, CartException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = cdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("Invalid authKey"));

		pdao.findById(productId).orElseThrow(() -> new ProductException("Product not found with id : " + productId));

		Cart cart = cartdao.findByCustomer_idAndProduct_id(customer.getId(), productId)
				.orElseThrow(() -> new CartException("Product not found"));

		if (quantity == 0) {
			cartdao.delete(cart);
		} else {
			cart.setQuantity(quantity);
			cartdao.save(cart);
		}

		return "quantity updated";

	}

	@Override
	public String removeFromCart(Integer productId, String authKey) throws ProductException, CartException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = cdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("Invalid authKey"));

		pdao.findById(productId).orElseThrow(() -> new ProductException("Product not found with id : " + productId));

		Cart cart = cartdao.findByCustomer_idAndProduct_id(customer.getId(), productId)
				.orElseThrow(() -> new CartException("Product not found"));

		cartdao.delete(cart);

		return "Product removed from cart";

	}

	@Override
	public CartDto getCartItems(String authKey) throws CartException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = cdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("Invalid authKey"));

		List<Cart> carts = cartdao.findAllByCustomerOrderByCreatedDateDesc(customer);

		if (carts.size() == 0)
			throw new CartException("Cart is empty");

		List<CartItemDto> cartItemDtos = new ArrayList<>();
		Double totalPrice = (double) 0;

		for (Cart c : carts) {

			CartItemDto cid = new CartItemDto();
			cid.setId(c.getId());
			cid.setProduct(c.getProduct());
			cid.setQuantity(c.getQuantity());

			totalPrice += c.getQuantity() * c.getProduct().getPrice();

			cartItemDtos.add(cid);

		}

		CartDto cd = new CartDto();

		cd.setCartItems(cartItemDtos);
		cd.setTotalPrice(totalPrice);

		return cd;

	}

}
