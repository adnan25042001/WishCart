package com.wishcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutItemDto {

	private String productName;
	private Integer quantity;
	private Double price;
	private Integer productId;
	private Integer customerId;

}
