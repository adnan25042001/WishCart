package com.wishcart.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

	private List<CartItemDto> cartItems;
	private Double totalPrice;

}
