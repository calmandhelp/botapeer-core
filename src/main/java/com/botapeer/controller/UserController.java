package com.botapeer.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.controller.payload.user.UpdatePasswordRequest;
import com.botapeer.controller.payload.user.UserRequest;
import com.botapeer.controller.payload.user.UserResponse;
import com.botapeer.domain.service.IUserService;
import com.botapeer.usecase.IUserUsecase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

	private final IUserService userService;
	private final IUserUsecase userUsecase;

	@GetMapping("/users")
	public Collection<UserResponse> findUsers(@RequestParam(required = false) String username) {
		Collection<UserResponse> u = userUsecase.findUsers(username);
		return u;
	}

	@GetMapping("/users/{userId}")
	public Optional<UserResponse> findById(@PathVariable String userId) {
		Optional<UserResponse> u = userUsecase.findById(userId);
		return u;
	}

	@PostMapping("/users/{userId}")
	public Optional<UserResponse> updateUser(Principal principal,
			@Validated @RequestPart("formData") UserRequest user,
			@RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
			@RequestPart(value = "coverImage", required = false) MultipartFile coverImage,
			BindingResult result) {
		Optional<UserResponse> r = userUsecase.update(principal, user, profileImage, coverImage, result);

		return r;
	}

	@PostMapping("/users/{userId}/password")
	public Optional<UserResponse> updatePassword(Principal principal,
			@RequestBody @Validated UpdatePasswordRequest request,
			BindingResult result) {
		Optional<UserResponse> r = userUsecase.updatePassword(principal, request, result);
		return r;
	}

	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable String userId) {
		userUsecase.delete(userId);
	}

	@GetMapping("/findByEmail")
	public Optional<UserResponse> findByEmail(@RequestParam String email) {
		Optional<UserResponse> r = userUsecase.findByEmail(email);
		return r;
	}

}
