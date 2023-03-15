package com.wishcart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Size(min = 3, max = 30, message = "name should be minimum 3 and maximum 30 character")
	private String cardHolder;

	@Column(unique = true)
	@Size(min = 16, max = 16, message = "Card number should be of 16 digits")
	private String cardNumber;

	@Size(min = 5, max = 5)
	private String validThru;

	@Column(unique = true)
	@Size(min = 3, max = 3)
	private Integer cvv;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
