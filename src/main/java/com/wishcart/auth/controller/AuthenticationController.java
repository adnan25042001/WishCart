package com.wishcart.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.auth.model.AuthenticationRequest;
import com.wishcart.auth.model.AuthenticationResponse;
import com.wishcart.auth.model.RegisterRequest;
import com.wishcart.auth.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

	private final AuthenticationService authService;

	@Operation(
			description = "There are three roles admin, seller and user",
			summary = "Endpoint for registering a new user"
	)
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return new ResponseEntity<AuthenticationResponse>(authService.register(request), HttpStatus.CREATED);
	}

	@Operation(
			summary = "Endpoint for login"
	)
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return new ResponseEntity<AuthenticationResponse>(authService.authenticate(request), HttpStatus.OK);
	}

}
