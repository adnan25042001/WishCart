package com.wishcart.service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishcart.config.MySecurityConfig;
import com.wishcart.dto.AdminSignupDto;
import com.wishcart.dto.CustomerSignupDto;
import com.wishcart.dto.SessionDto;
import com.wishcart.dto.UserDto;
import com.wishcart.exception.AdminException;
import com.wishcart.exception.CustomerException;
import com.wishcart.model.Admin;
import com.wishcart.model.CurrentUserSession;
import com.wishcart.model.Customer;
import com.wishcart.model.UserType;
import com.wishcart.repository.AdminDao;
import com.wishcart.repository.CurrentUserSessionDao;
import com.wishcart.repository.CustomerDao;

@Service
public class LoginSignupServiceImpl implements LoginSignupService {

	@Autowired
	private AdminDao adao;

	@Autowired
	private CustomerDao cdao;

	@Autowired
	private CurrentUserSessionDao cusdao;

	@Override
	public SessionDto customerSignup(CustomerSignupDto customer) throws CustomerException {

		Optional<Customer> opt = cdao.findByEmail(customer.getEmail());

		Optional<Admin> opt1 = adao.findByEmail(customer.getEmail());

		if (opt.isPresent())
			throw new CustomerException("Account already exist with email : " + customer.getEmail());

		if (opt1.isPresent())
			throw new CustomerException("account already exist with email : " + customer.getEmail());

		SessionDto sdt = new SessionDto();

		Customer cust = new Customer();

		cust.setAddress(customer.getAddress());
		cust.setEmail(customer.getEmail());
		cust.setMobile(customer.getMobile());
		cust.setName(customer.getName());

		String encryptedPassword = customer.getPassword();

		try {
			encryptedPassword = MySecurityConfig.passwordEncoder(encryptedPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		cust.setPassword(encryptedPassword);

		cdao.save(cust);

		sdt.setAuthkey(UUID.randomUUID().toString());
		sdt.setSessionTime(LocalDateTime.now());

		CurrentUserSession cus = new CurrentUserSession();
		cus.setAuthKey(sdt.getAuthkey());
		cus.setEmail(customer.getEmail());
		cus.setSessionTime(sdt.getSessionTime());
		cus.setUserType(UserType.CUSTOMER);

		cusdao.save(cus);

		return sdt;

	}

	@Override
	public SessionDto adminSignup(AdminSignupDto admin) throws AdminException {

		Optional<Admin> opt = adao.findByEmail(admin.getEmail());

		Optional<Customer> opt1 = cdao.findByEmail(admin.getEmail());

		if (opt.isPresent())
			throw new AdminException("account already exist with email : " + admin.getEmail());

		if (opt1.isPresent())
			throw new AdminException("account already exist with email : " + admin.getEmail());

		SessionDto sdt = new SessionDto();

		Admin adm = new Admin();

		adm.setCompanyAddress(admin.getCompanyName());
		adm.setCompanyName(admin.getCompanyName());
		adm.setEmail(admin.getEmail());
		adm.setMobile(admin.getMobile());
		adm.setName(admin.getName());

		String encryptedPassword = admin.getPassword();

		try {
			encryptedPassword = MySecurityConfig.passwordEncoder(encryptedPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		adm.setPassword(encryptedPassword);

		adao.save(adm);

		sdt.setAuthkey(UUID.randomUUID().toString());
		sdt.setSessionTime(LocalDateTime.now());

		CurrentUserSession cus = new CurrentUserSession();
		cus.setAuthKey(sdt.getAuthkey());
		cus.setEmail(admin.getEmail());
		cus.setSessionTime(sdt.getSessionTime());
		cus.setUserType(UserType.ADMIN);

		cusdao.save(cus);

		return sdt;

	}

	@Override
	public SessionDto loginAdmin(UserDto user) throws AdminException {

		Admin admin = adao.findByEmail(user.getEmail())
				.orElseThrow(() -> new AdminException("wrong email or password"));

		try {
			if (!admin.getPassword().equals(MySecurityConfig.passwordEncoder(user.getPassword())))
				throw new AdminException("Wrong email or password");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		Optional<CurrentUserSession> opt1 = cusdao.findByEmail(user.getEmail());

		if (opt1.isPresent())
			throw new AdminException("Admin already logged in!");

		SessionDto sdt = new SessionDto();
		sdt.setAuthkey(UUID.randomUUID().toString());
		sdt.setSessionTime(LocalDateTime.now());

		CurrentUserSession cus = new CurrentUserSession();
		cus.setAuthKey(sdt.getAuthkey());
		cus.setEmail(user.getEmail());
		cus.setSessionTime(sdt.getSessionTime());
		cus.setUserType(UserType.ADMIN);

		cusdao.save(cus);

		return sdt;

	}

	@Override
	public SessionDto loginCustomer(UserDto user) throws CustomerException {

		Customer customer = cdao.findByEmail(user.getEmail())
				.orElseThrow(() -> new CustomerException("Wrong email or password"));

		try {
			if (!customer.getPassword().equals(MySecurityConfig.passwordEncoder(user.getPassword())))
				throw new CustomerException("Wrong email or password");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		Optional<CurrentUserSession> opt1 = cusdao.findByEmail(user.getEmail());

		if (opt1.isPresent()) {

			throw new CustomerException("Customer already logged in!");

		}

		SessionDto sdt = new SessionDto();
		sdt.setAuthkey(UUID.randomUUID().toString());
		sdt.setSessionTime(LocalDateTime.now());

		CurrentUserSession cus = new CurrentUserSession();
		cus.setAuthKey(sdt.getAuthkey());
		cus.setEmail(user.getEmail());
		cus.setSessionTime(sdt.getSessionTime());
		cus.setUserType(UserType.CUSTOMER);

		cusdao.save(cus);

		return sdt;

	}

	@Override
	public String logout(String authKey) throws CustomerException, AdminException {

		Optional<CurrentUserSession> opt = cusdao.findByAuthKey(authKey);

		if (opt.isEmpty())
			throw new RuntimeException("User alresdy logged out!");

		CurrentUserSession cus = opt.get();

		cusdao.delete(cus);

		return "Logged out...";

	}

}
