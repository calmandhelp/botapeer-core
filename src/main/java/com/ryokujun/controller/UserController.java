package com.ryokujun.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ryokujun.domain.entity.User;
import com.ryokujun.domain.service.IUserService;
import com.ryokujun.usecase.IUserUsecase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

	private final IUserService userService;
	private final IUserUsecase userUsecase;

	@GetMapping("/users/{userId}")
	public Optional<User> getUser(@PathVariable String userId) {
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

	@GetMapping("/users")
	public Collection<User> getUserList() {
		Collection<User> users = userService.findAll();
		return users;
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
