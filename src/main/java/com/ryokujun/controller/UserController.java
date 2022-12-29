package com.ryokujun.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryokujun.constants.ResponseConstants;
import com.ryokujun.controller.exception.validation.ErrorMessages;
import com.ryokujun.controller.exception.validation.ValidationException;
import com.ryokujun.domain.entity.User;
import com.ryokujun.domain.service.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final IUserService userService;

	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable String userId) {
		try {
			int userIdForget = Integer.parseInt(userId);
			User u = userService.findById(userIdForget);
			return u;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@PostMapping("/users/{userId}")
	public User updateUser(@PathVariable String userId, @Validated @RequestBody User user,
			BindingResult result) {
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
		try {
			int userIdforUpdate = Integer.parseInt(userId);
			user.setId(userIdforUpdate);
			if (!userService.update(user)) {
				throw new Error();
			}
			User u = userService.findById(userIdforUpdate);
			return u;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable String userId) {
		try {
			int id = Integer.parseInt(userId);
			if (!userService.delete(id)) {
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

	@PostMapping("/users")
	public User createUser(@Validated @RequestBody User user, BindingResult result) {
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

		User u = userService.findByEmail(user.getEmail());
		return u;
	}

	@GetMapping("/findByEmail")
	public User findByEmail(@RequestParam String email) {
		try {
			User u = userService.findByEmail(email);
			return u;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
