package com.botapeer.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.botapeer.security.JwtTokenProvider;
import com.botapeer.usecase.interfaces.IUserUsecase;

import api.AuthApi;
import lombok.RequiredArgsConstructor;
import model.CreateUserRequest;
import model.JwtAuthenticationResponse;
import model.SignInRequest;
import model.UserResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final IUserUsecase userUsecase;

	@Override
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@Valid @RequestBody SignInRequest signInRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUsernameOrEmail(),
						signInRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtTokenProvider.generateToken(authentication);
		JwtAuthenticationResponse response = new JwtAuthenticationResponse();
		response.setAccessToken(jwt);
		response.setTokenType("Bearer");
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping("/signup")
	public ResponseEntity<UserResponse> createUser(@Validated @RequestBody CreateUserRequest request) {

		Optional<UserResponse> response = userUsecase.create(request);

		return ResponseEntity.ok(response.get());
	}

}
