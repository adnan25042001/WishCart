package com.wishcart.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wishcart.exception.CartException;
import com.wishcart.exception.ProductException;
import com.wishcart.exception.UserException;
import com.wishcart.exception.WishlistException;
import com.wishcart.model.Cart;
import com.wishcart.model.Product;
import com.wishcart.model.SuccessMessage;
import com.wishcart.model.User;
import com.wishcart.model.Wishlist;
import com.wishcart.repository.CartRepository;
import com.wishcart.repository.ProductRepository;
import com.wishcart.repository.UserRepository;
import com.wishcart.repository.WishlistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

	private final WishlistRepository wishlistRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final CartRepository cartRepository;

	@Override
	public SuccessMessage addToWishlist(Long productId, String email) throws ProductException, WishlistException {

		var user = getUser(email);
		var product = getProduct(productId);

		var opt = wishlistRepository.findByUserAndProduct(user, product);

		if (opt.isPresent())
			throw new WishlistException("Product already present in wishlist");

		var wishlist = Wishlist.builder().addedDate(LocalDateTime.now()).product(product).user(user).build();

		return SuccessMessage.builder().success(true).data(List.of(wishlistRepository.save(wishlist))).size(1).build();

	}

	@Override
	public SuccessMessage removeFromWishlist(Long wishlistId, String email) throws ProductException, WishlistException {

		var wishlist = wishlistRepository.findById(wishlistId)
				.orElseThrow(() -> new WishlistException("Invalid wishlist id: " + wishlistId));

		if (!wishlist.getUser().equals(getUser(email)))
			throw new CartException("Invalid wishlist id: " + wishlistId);

		wishlistRepository.delete(wishlist);

		return SuccessMessage.builder().success(true).data(List.of("Product successfully removed from wishlist"))
				.size(1).build();

	}

	@Override
	public SuccessMessage getWishlistOfUser(String email) {

		var user = getUser(email);

		List<Wishlist> wishlists = wishlistRepository.findAllByUserOrderByAddedDateDesc(user);

		if (wishlists.size() == 0)
			throw new WishlistException("Wishlist is empty");

		return SuccessMessage.builder().success(true).data(wishlists).size(wishlists.size()).build();

	}

	@Override
	public SuccessMessage addToCart(Long wishlistId, String email) throws ProductException {

		var user = getUser(email);

		var wishlist = wishlistRepository.findById(wishlistId)
				.orElseThrow(() -> new WishlistException("Wrong wishlist id: " + wishlistId));

		Optional<Cart> opt = cartRepository.findByUserAndProduct(user, wishlist.getProduct());

		if (opt.isPresent())
			throw new ProductException("Product already present in cart");

		var cart = Cart.builder().addedDate(LocalDateTime.now()).product(wishlist.getProduct()).quantity(1).user(user)
				.build();

		wishlistRepository.delete(wishlist);

		return SuccessMessage.builder().success(true).data(List.of(cartRepository.save(cart))).size(1).build();

	}

	private User getUser(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
	}

	private Product getProduct(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ProductException("Invalid product id: " + productId));
	}

}
