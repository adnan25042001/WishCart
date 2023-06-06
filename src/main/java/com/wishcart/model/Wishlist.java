package com.wishcart.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wishlist {

	@Id
	@GeneratedValue
	private Long id;

	private Long size;

	@OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
	private List<WishlistItem> wishlistItems;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "customer_id")
	private User customer;

}
