package com.wishcart.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.model.SuccessMessage;
import com.wishcart.service.CartService;
import com.wishcart.service.ProductService;
import com.wishcart.service.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final ProductService productService;
	private final CartService cartService;
	private final WishlistService wishlistService;

	@GetMapping("/product")
	public ResponseEntity<SuccessMessage> getProductByIdHandler(@RequestParam("id") Long id, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getProductById(id, email), HttpStatus.OK);
	}

	@GetMapping("/product/search")
	public ResponseEntity<SuccessMessage> getProductByIdHandler(@RequestParam("q") String name, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getProductByName(name, email), HttpStatus.OK);
	}

	@GetMapping("/products/getall")
	public ResponseEntity<SuccessMessage> getAllProductHandler(Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getAllProducts(email), HttpStatus.OK);
	}

	@GetMapping("/product/betweenprice")
	public ResponseEntity<SuccessMessage> getProductBetweenPriceHandler(@RequestParam("min") Double min,
			@RequestParam("max") Double max) {
		return new ResponseEntity<SuccessMessage>(productService.getProductBetweenPrice(min, max), HttpStatus.OK);
	}

	@GetMapping("/products/bycategory")
	public ResponseEntity<SuccessMessage> getAllProductByCategoryHandler(@RequestParam("id") Long id,
			Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(productService.getProductsByCategoryId(id, email), HttpStatus.OK);
	}

	@PostMapping("/cart/add")
	public ResponseEntity<SuccessMessage> addProductToCartHandler(@RequestParam("id") Long productId,
			@RequestParam("quantity") Integer quantity, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(cartService.addToCart(productId, quantity, email),
				HttpStatus.CREATED);
	}

	@PutMapping("/cart/update")
	public ResponseEntity<SuccessMessage> updateProductQuantityInCartHandler(@RequestParam("id") Long cartId,
			@RequestParam("quantity") Integer quantity, Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(cartService.updateProductQuantity(cartId, quantity, email),
				HttpStatus.OK);
	}

	@DeleteMapping("/cart/remove")
	public ResponseEntity<SuccessMessage> removeProductFromCartHandler(@RequestParam("id") Long cartId,
			Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(cartService.removeFromCart(cartId, email), HttpStatus.OK);
	}

	@GetMapping("/cart/getall")
	public ResponseEntity<SuccessMessage> getAllCartItemsHandler(Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(cartService.getAllCartItems(email), HttpStatus.OK);
	}

	@DeleteMapping("/cart/removeall")
	public ResponseEntity<SuccessMessage> removeAllProductsFromCartHandler(Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(cartService.removeAllCart(email), HttpStatus.OK);
	}

	@PostMapping("/wishlist/add")
	public ResponseEntity<SuccessMessage> addProductToWishlistHandler(@RequestParam("id") Long productId,
			Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(wishlistService.addToCart(productId, email), HttpStatus.CREATED);
	}

	@DeleteMapping("/wishlist/remove")
	public ResponseEntity<SuccessMessage> removeProductFromWishlistHandler(@RequestParam("id") Long wishlistId,
			Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(wishlistService.removeFromWishlist(wishlistId, email),
				HttpStatus.CREATED);
	}

	@GetMapping("/wishlist/getall")
	public ResponseEntity<SuccessMessage> getAllWishlistItemsHandler(Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(wishlistService.getWishlistOfUser(email), HttpStatus.CREATED);
	}

	@PutMapping("/wishlist/cart/add")
	public ResponseEntity<SuccessMessage> MoveProductFromWishlistToCartHandler(@RequestParam("id") Long wishlistId,
			Principal principal) {
		String email = principal.getName();
		return new ResponseEntity<SuccessMessage>(wishlistService.addToCart(wishlistId, email), HttpStatus.CREATED);
	}

}
