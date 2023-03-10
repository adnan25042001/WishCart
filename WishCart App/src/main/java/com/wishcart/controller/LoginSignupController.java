package com.wishcart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.dto.CustomerSignupDto;
import com.wishcart.dto.SessionDto;
import com.wishcart.dto.UserDto;
import com.wishcart.service.LoginSignupService;

@RestController
@RequestMapping("/wishcart")
public class LoginSignupController {

	@Autowired
	private LoginSignupService lss;

	@PostMapping("/login/customer")
	public ResponseEntity<SessionDto> customerLoginHandler(@Valid @RequestBody UserDto user) {

		SessionDto sdt = lss.loginCustomer(user);

		return new ResponseEntity<SessionDto>(sdt, HttpStatus.OK);

	}

	@PostMapping("/login/admin")
	public ResponseEntity<SessionDto> adminLoginHandler(@Valid @RequestBody UserDto user) {

		SessionDto sdt = lss.loginAdmin(user);

		return new ResponseEntity<SessionDto>(sdt, HttpStatus.OK);

	}

	@PostMapping("/signup/customer")
	public ResponseEntity<SessionDto> signupHandler(@Valid @RequestBody CustomerSignupDto customer) {

		SessionDto sdt = lss.customerSignup(customer);

		return new ResponseEntity<SessionDto>(sdt, HttpStatus.CREATED);

	}

//	@PostMapping("/signup/admin")
//	public ResponseEntity<SessionDTO> signupHandler(@Valid @RequestBody AdminSignupDTO admin) {
//
//		SessionDTO sdt = lss.adminSignup(admin);
//
//		return new ResponseEntity<SessionDTO>(sdt, HttpStatus.CREATED);
//
//	}

	@DeleteMapping("/logout/{authkey}")
	public ResponseEntity<String> logout(@PathVariable("authkey") String authKey) {

		String msg = lss.logout(authKey);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
