package com.wishcart.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminSignupDTO {

	@NotBlank
	@NotEmpty
	@Size(min = 3, max = 20, message = "Name shuld contain minimum 3 and maximum 20 character")
	private String name;

	@Email(message = "Please provide a valid email")
	private String email;

	@NotNull
	@Pattern(regexp = "^[789][0-9]{9}", message = "Mobile number should be of 10 digits and start with 7, 8 or 9")
	private String mobile;

	@NotBlank
	@NotEmpty
	@Size(min = 3, max = 30, message = "Address shuld contain minimum 3 and maximum 30 character")
	private String address;

	@Pattern(regexp = "^(^[a-zA-Z0-9]{4,12}$)", message = "password must contain atleast 1 uppercase, 1 lowercase, and 1 digit ")
	private String password;

	@NotBlank
	@NotEmpty
	@Size(min = 3, max = 30, message = "Address shuld contain minimum 3 and maximum 30 character")
	private String companyName;

}
