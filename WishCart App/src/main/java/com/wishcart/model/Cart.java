package com.wishcart.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private LocalDateTime createdDate;

	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

}
