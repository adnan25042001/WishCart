package com.wishcart.service;

import com.wishcart.dto.AdminSignupDto;
import com.wishcart.dto.CustomerSignupDto;
import com.wishcart.dto.SessionDto;
import com.wishcart.dto.UserDto;
import com.wishcart.exception.AdminException;
import com.wishcart.exception.CustomerException;

public interface LoginSignupService {

	public SessionDto customerSignup(CustomerSignupDto customer) throws CustomerException;

	 public SessionDto adminSignup(AdminSignupDto admin) throws AdminException;

	public SessionDto loginAdmin(UserDto user) throws AdminException;

	public SessionDto loginCustomer(UserDto user) throws CustomerException;

	public String logout(String authKey) throws CustomerException, AdminException;

}
