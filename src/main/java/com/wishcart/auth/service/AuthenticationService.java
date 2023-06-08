package com.wishcart.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.wishcart.auth.model.AuthenticationRequest;
import com.wishcart.auth.model.AuthenticationResponse;
import com.wishcart.auth.model.RegisterRequest;
import com.wishcart.config.JwtService;
import com.wishcart.exception.UserException;
import com.wishcart.model.Role;
import com.wishcart.model.Token;
import com.wishcart.model.TokenType;
import com.wishcart.model.User;
import com.wishcart.repository.TokenRepository;
import com.wishcart.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {

		System.out.println(request);

		Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

		if (existingUser.isPresent())
			throw new UserException("User already exists");

		Role role;

		if ((request.getRole()).equalsIgnoreCase("ADMIN")) {
			role = Role.ADMIN;
		} else if ((request.getRole()).equalsIgnoreCase("SELLER")) {
			role = Role.SELLER;
		} else {
			role = Role.USER;
		}

		User user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
				.address(request.getAddress()).mobile(request.getMobile()).role(role).build();

		System.out.println(user);

		User savedUser = userRepository.save(user);

		System.out.println(savedUser);

		var jwtToken = jwtService.generateToken(user);

		saveUserToken(savedUser, jwtToken);

		return AuthenticationResponse.builder().success(true).token(jwtToken).timeStamp(LocalDateTime.now()).build();

	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		var user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new UserException("Wrong Email or Password!"));

		var jwtToken = jwtService.generateToken(user);

		saveUserToken(user, jwtToken);

		return AuthenticationResponse.builder().success(true).token(jwtToken).timeStamp(LocalDateTime.now()).build();

	}

	private void saveUserToken(User user, String jwtToken) {
		Token token = Token.builder().token(jwtToken).tokenType(TokenType.BEARER).user(user).build();

		tokenRepository.save(token);
	}

}
