package com.wishcart.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String productName;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "product_id")
	private Set<Image> images;

	@NotNull
	@Min(value = 0, message = "price should be greater than 0")
	private Double price;

	@Lob
	private String description;

	@ManyToMany(mappedBy = "products")
	private List<Category> categories;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "seller_id")
	private User seller;

}
