package com.wishcart.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessMessage {

	private boolean success = true;
	private Integer totalResult;
	private List<?> result;
	
}
