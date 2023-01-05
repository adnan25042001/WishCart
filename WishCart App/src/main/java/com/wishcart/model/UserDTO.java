package com.wishcart.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {

	@NotBlank
	@NotEmpty
	@Size(min = 3, max = 20, message = "Name shuld contain minimum 3 and maximum 20 character")
	private String email;

	@NotNull(message = "Password should not be null")
	@NotBlank(message = "Password should not be blanck")
	private String password;

}
