package com.wishcart.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Order {

	private Integer orderId;
	private LocalDate orderdate;
	private String status;

}
