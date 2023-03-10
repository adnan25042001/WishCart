package com.wishcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	private Integer productId;
	private String productName;
	private String Image1;
	private String Image2;
	private String Image3;
	private Double price;
	private String description;
	private Integer quantity;
	private Integer category_id;

}
