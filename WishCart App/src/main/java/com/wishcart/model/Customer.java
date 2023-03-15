package com.wishcart.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "Name should not be null!")
	@NotBlank(message = "Name should not be blanck!")
	@Size(min = 2, max = 20, message = "Name should contain atleast 3 and atmost 20 characters")
	private String name;

	@Email
	@NotNull(message = "Email should not be null!")
	private String email;

	@NotNull(message = "Mobile number is mandatory")
	private String mobile;

	@NotNull(message = "Address number is mandatory")
	private String address;

	@Pattern(regexp = "^(^[a-zA-Z0-9]{4,50}$)", message = "password must contain atleast 1 uppercase, 1 lowercase, and 1 digit ")
	private String password;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
	private List<Card> cardDetails;

}
