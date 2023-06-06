package com.wishcart.auth.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishcart.auth.model.AuthenticationRequest;
import com.wishcart.auth.model.AuthenticationResponse;
import com.wishcart.auth.model.RegisterRequest;
import com.wishcart.auth.service.AuthenticationServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationServiceImpl authService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return new ResponseEntity<AuthenticationResponse>(authService.register(request), HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return new ResponseEntity<AuthenticationResponse>(authService.authenticate(request), HttpStatus.OK);
	}

}
