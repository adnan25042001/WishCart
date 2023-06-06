package com.wishcart.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WishlistItem {

	@Id
	@GeneratedValue
	private Long id;

	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "wishlist_id")
	private Wishlist wishlist;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

}
