package com.wishcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class removeCardDto {

	private String cardHolder;
	private String cardNumber;

}
