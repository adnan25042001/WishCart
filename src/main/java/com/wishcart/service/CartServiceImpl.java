package com.wishcart.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wishcart.exception.CartException;
import com.wishcart.exception.ProductException;
import com.wishcart.exception.UserException;
import com.wishcart.model.Cart;
import com.wishcart.model.Product;
import com.wishcart.model.SuccessMessage;
import com.wishcart.model.User;
import com.wishcart.repository.CartRepository;
import com.wishcart.repository.ProductRepository;
import com.wishcart.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	@Override
	public SuccessMessage addToCart(Long productId, Integer quantity, String email) throws ProductException {

		var user = getUser(email);
		var product = getProduct(productId);

		var opt = cartRepository.findByUserAndProduct(user, product);

		if (opt.isPresent())
			throw new CartException("Product already present in cart");

		var cart = Cart.builder().addedDate(LocalDateTime.now()).product(product).user(user).quantity(quantity).build();

		return SuccessMessage.builder().success(true).data(List.of(cartRepository.save(cart))).size(1).build();

	}

	@Override
	public SuccessMessage updateProductQuantity(Long cartId, Integer quantity, String email)
			throws ProductException, CartException {

		var user = getUser(email);

		var cart = cartRepository.findById(cartId).orElseThrow(() -> new CartException("Product not present in cart"));

		if (user.getId() != cart.getUser().getId())
			throw new UserException("Unauthorized");

		cart.setQuantity(quantity);

		return SuccessMessage.builder().success(true).data(List.of(cart)).size(1).build();
	}

	@Override
	public SuccessMessage removeFromCart(Long cartId, String email) throws ProductException, CartException {

		var cart = cartRepository.findById(cartId).orElseThrow(() -> new CartException("Invalid cart id: " + cartId));

		if (!cart.getUser().equals(getUser(email)))
			throw new CartException("Invalid cart id: " + cartId);

		cartRepository.delete(cart);

		return SuccessMessage.builder().success(true).data(List.of("Product successfully removed from cart")).size(1)
				.build();

	}

	@Override
	public SuccessMessage getAllCartItems(String email) throws CartException {

		var user = getUser(email);

		List<Cart> cartItems = cartRepository.findAllByUserOrderByAddedDateDesc(user);

		if (cartItems.size() == 0)
			throw new CartException("Cart is empty");

		return SuccessMessage.builder().success(true).data(cartItems).size(cartItems.size()).build();

	}

	@Override
	public SuccessMessage removeAllCart(String email) throws CartException {

		var user = getUser(email);

		List<Cart> cartItems = cartRepository.findAllByUserOrderByAddedDateDesc(user);

		if (cartItems.size() == 0)
			throw new CartException("Cart is empty");

		cartRepository.deleteAll(cartItems);

		return SuccessMessage.builder().success(true).data(List.of("Products successfully removed from cart")).size(1)
				.build();

	}

	private User getUser(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
	}

	private Product getProduct(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ProductException("Invalid product id: " + productId));
	}

}
