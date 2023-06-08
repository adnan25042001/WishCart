package com.wishcart.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@GetMapping("/get")
	public ResponseEntity<String> getHello(Principal principal) {
		String user = principal.getName();
		System.out.println(user);
		return new ResponseEntity<String>(user + "Hello", HttpStatus.OK);
	}

}
