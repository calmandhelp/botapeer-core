package com.ryokujun.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryokujun.constants.ResponseConstants;
import com.ryokujun.controller.exception.validation.ErrorMessages;
import com.ryokujun.controller.exception.validation.ValidationException;
import com.ryokujun.domain.entity.User;
import com.ryokujun.domain.service.IUserService;
import com.ryokujun.payload.auth.JwtAuthenticationResponse;
import com.ryokujun.payload.auth.LoginRequest;
import com.ryokujun.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final IUserService userService;

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println("siginin");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/signup")
	public Optional<User> createUser(@Validated @RequestBody User user, BindingResult result) {
		if (result.hasErrors()) {
			List<HashMap<String, String>> list = new ArrayList<>();
			for (int i = 0; i < result.getErrorCount(); i++) {
				HashMap<String, String> errorsMap = new HashMap<>();
				errorsMap.put(ResponseConstants.ERRORS_CODE_KEY, result.getAllErrors().get(i).getCode());
				errorsMap.put(ResponseConstants.ERRORS_MESSAGE_KEY, result.getAllErrors().get(i).getDefaultMessage());
				list.add(errorsMap);
			}
			ErrorMessages errorMessages = new ErrorMessages();
			errorMessages.setMessages(list);
			throw new ValidationException(errorMessages);
		}

		if (!userService.create(user)) {
			throw new Error();
		}

		Optional<User> u = userService.findByEmail(user.getEmail());
		return u;
	}

}
