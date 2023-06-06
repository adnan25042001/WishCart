package com.wishcart.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	private String productName;
	private Set<String> images;
	private Double price;
	private String description;
	private Set<Long> categoryId;

}
