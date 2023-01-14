package com.ryokujun.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.ryokujun.constants.ResponseConstants;
import com.ryokujun.controller.exception.validation.ErrorMessages;
import com.ryokujun.controller.exception.validation.ValidationException;
import com.ryokujun.domain.entity.User;
import com.ryokujun.domain.service.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

	private final IUserService userService;

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
	public Optional<User> updateUser(@PathVariable String userId, @Validated @RequestBody User user,
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
			int userIdInt = Integer.parseInt(userId);
			user.setId(userIdInt);
			if (!userService.update(user)) {
				throw new Error();
			}
			Optional<User> u = userService.findById((long) userIdInt);
			return u;
		} catch (Exception e) {
			System.out.println(e);
		}
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
