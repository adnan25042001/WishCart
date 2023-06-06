package com.wishcart.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

	private String firstname;
	private String lastname;
	private String email;
	private String mobile;
	private String address;
	private String password;
	private String role;

}
