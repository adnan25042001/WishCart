package com.wishcart.service;

import com.wishcart.exception.AdminException;
import com.wishcart.exception.CustomerException;
import com.wishcart.model.CustomerSignupDTO;
import com.wishcart.model.SessionDTO;
import com.wishcart.model.UserDTO;

public interface LoginSignupService {

	public SessionDTO customerSignup(CustomerSignupDTO customer) throws CustomerException;

	// public SessionDTO adminSignup(AdminSignupDTO admin) throws AdminException;

	public SessionDTO loginAdmin(UserDTO user) throws AdminException;

	public SessionDTO loginCustomer(UserDTO user) throws CustomerException;

	public String logout(String authKey) throws CustomerException, AdminException;

}
