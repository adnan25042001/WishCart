package com.wishcart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishcart.exception.CustomerException;
import com.wishcart.exception.ProductException;
import com.wishcart.exception.WishlistException;
import com.wishcart.model.CurrentUserSession;
import com.wishcart.model.Customer;
import com.wishcart.model.Product;
import com.wishcart.model.Wishlist;
import com.wishcart.repository.CurrentUserSessionDao;
import com.wishcart.repository.CustomerDao;
import com.wishcart.repository.ProductDao;
import com.wishcart.repository.WishlistDao;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistDao wdao;

	@Autowired
	private ProductDao pdao;

	@Autowired
	private CustomerDao cdao;

	@Autowired
	private CurrentUserSessionDao cusdao;

	@Override
	public String addToWishlist(Integer productId, String authKey) throws ProductException, WishlistException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = cdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("Invalid authKey"));

		Product product = pdao.findById(productId)
				.orElseThrow(() -> new ProductException("Product not found with id : " + productId));

		Optional<Wishlist> opt = wdao.findByCustomer_idAndProduct_id(customer.getId(), productId);

		if (opt.isPresent()) {
			throw new WishlistException("Product already wishlisted");
		}

		Wishlist wishlist = new Wishlist();
		wishlist.setCustomer(customer);
		wishlist.setProduct(product);

		wdao.save(wishlist);

		return "product added to wishlist";

	}

	@Override
	public String removeFromWishlist(Integer productId, String authKey) throws ProductException, WishlistException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = cdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("Invalid authKey"));

		pdao.findById(productId).orElseThrow(() -> new ProductException("Product not found with id : " + productId));

		Wishlist wl = wdao.findByCustomer_idAndProduct_id(customer.getId(), productId)
				.orElseThrow(() -> new WishlistException("Product not present in wishlist"));

		wdao.delete(wl);

		return "Product removed from wishlist";

	}

	@Override
	public List<Wishlist> getWishlistOfUser(String authKey) {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = cdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("Invalid authKey"));

		List<Wishlist> wishlist = wdao.findByCustomer_id(customer.getId());

		if (wishlist.size() == 0) {
			throw new WishlistException("Wishlist is empty");
		}

		return wishlist;

	}

}
