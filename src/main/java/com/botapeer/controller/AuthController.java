package com.botapeer.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.botapeer.controller.payload.auth.JwtAuthenticationResponse;
import com.botapeer.controller.payload.auth.LoginRequest;
import com.botapeer.domain.service.IUserService;
import com.botapeer.security.JwtTokenProvider;
import com.botapeer.usecase.IUserUsecase;
import com.botapeer.util.ValidationUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final IUserService userService;
	private final IUserUsecase userUsecase;
	private final ValidationUtils validationUtils;

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println("siginin");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	//	@PostMapping("/signup")
	//	public Optional<UserResponse> createUser(@Validated @RequestBody UserRequest user, BindingResult result) {
	//
	//		validationUtils.validation(result);
	//
	//		if (!userUsecase.create(user)) {
	//			throw new Error();
	//		}
	//
	//		return null;
	//	}

}
