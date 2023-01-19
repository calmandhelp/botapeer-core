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

import com.botapeer.domain.entity.User;
import com.botapeer.domain.service.IUserService;
import com.botapeer.payload.user.UpdatePasswordRequest;
import com.botapeer.usecase.IUserUsecase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

	private final IUserService userService;
	private final IUserUsecase userUsecase;

	@GetMapping("/users")
	public Collection<User> findUsers(@RequestParam(required = false) String username) {
		Collection<User> u = userService.findUsers(username);
		System.out.println(u);
		return u;
	}

	@GetMapping("/users/{userId}")
	public Optional<User> findById(@PathVariable String userId) {
		try {
			int userIdInt = Integer.parseInt(userId);
			Optional<User> u = userService.findById((long) userIdInt);
			return u;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@PostMapping("/users/{userId}")
	public Optional<User> updateUser(Principal principal,
			@Validated @RequestPart("formData") User user,
			@RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
			@RequestPart(value = "coverImage", required = false) MultipartFile coverImage,
			BindingResult result) {
		Optional<User> u = userUsecase.update(principal, user, profileImage, coverImage, result);

		return u;
	}

	@PostMapping("/users/{userId}/password")
	public Optional<User> updatePassword(@RequestBody @Validated UpdatePasswordRequest request, BindingResult result) {
		Optional<User> u = userUsecase.updatePassword(request, result);
		return null;
	}

	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable String userId) {
		try {
			int userIdInt = Integer.parseInt(userId);
			if (!userService.delete((long) userIdInt)) {
				throw new Error();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@GetMapping("/findByEmail")
	public Optional<User> findByEmail(@RequestParam String email) {
		try {
			Optional<User> u = userService.findByEmail(email);
			return u;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
