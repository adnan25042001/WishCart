package com.wishcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;

	@NotNull
	private String productName;

	@NotNull
	private String Image1;
	private String Image2;
	private String Image3;

	@NotNull
	@Min(value = 0, message = "price should be greater than 0")
	private Double price;
	private String description;
	@NotNull
	@Min(value = 0, message = "price should be greater than 0")
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "category_cid")
	Category category;

}
