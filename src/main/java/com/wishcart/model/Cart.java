package com.wishcart.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cart {

	@Id
	@GeneratedValue
	private Long id;

	private LocalDateTime addedDate;

	@Min(value = 1)
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
