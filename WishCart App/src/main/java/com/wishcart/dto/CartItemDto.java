package com.wishcart.dto;

import com.wishcart.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

	private Integer Id;
	private Integer quantity;
	private Product product;

}
