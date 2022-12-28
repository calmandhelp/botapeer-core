package com.ryokujun.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ryokujun.controller.exception.ValidationException;
import com.ryokujun.entity.User;
import com.ryokujun.service.IUserService;
import com.ryokujun.util.errorUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final IUserService userService;

	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable String userId) {
		User u = userService.findById(userId);
		return u;
	}

	@PostMapping("/user/{userId}")
	public void updateUser(@PathVariable String userId, @Validated @RequestBody User user,
			BindingResult result) {
		if (result.hasErrors()) {
			String errorMessages = errorUtil.addAllErrors(result);

			throw new ValidationException(errorMessages);
		}
		int userIdforUpdate = Integer.parseInt(userId);
		user.setId(userIdforUpdate);
		userService.update(user);
	}

}
