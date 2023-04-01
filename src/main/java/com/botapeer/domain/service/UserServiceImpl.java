package com.botapeer.domain.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.botapeer.constants.ResponseConstants;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.repository.IUserRepository;
import com.botapeer.domain.service.interfaces.IUserService;
import com.botapeer.exception.NotFoundException;
import com.botapeer.util.ValidateUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final IUserRepository userRepository;
	private final Validator validator;

	@Override
	public Optional<User> findById(Long userId) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;

		validationMessage = ValidateUtils.validateNull(userId, "userId is null");
		validationMessage.ifPresent(msg -> errorMessages.put("userId_null", msg));
		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		validationMessage = ValidateUtils.validateZeroOrNegative(userId, "userId is zero or negative");
		validationMessage.ifPresent(msg -> errorMessages.put("userId_ZeroOrNegative", msg));
		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		return this.userRepository.findById(userId);
	}

	@Override
	public Collection<User> findUsers(String name) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNull(name, "name is null");
		validationMessage.ifPresent(msg -> errorMessages.put("name_null", msg));

		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		return this.userRepository.findUsers(name);
	}

	@Override
	public Integer create(User user, String encryptedPassword) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(user, "user is null or empty");
		validationMessage.ifPresent(msg -> errorMessages.put("user_nullOrEmpty", msg));
		validationMessage = ValidateUtils.validateNullOrEmpty(encryptedPassword, "encryptedPassword is null or empty");
		validationMessage.ifPresent(msg -> errorMessages.put("encryptedPassword_nullOrEmpty", msg));

		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		Set<ConstraintViolation<User>> violations = validator.validate(user);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

		user.setStatus(2);
		return this.userRepository.create(user, encryptedPassword);
	}

	@Override
	public boolean update(User user) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(user, "user is null or empty");
		validationMessage.ifPresent(msg -> errorMessages.put("user_nullOrEmpty", msg));

		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		Set<ConstraintViolation<User>> violations = validator.validate(user);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

		Optional<User> savedUser = findById((long) user.getId());
		if (!savedUser.isPresent()) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_USER_CODE);
		}

		validationMessage = ValidateUtils.validatePresent(user.getPassword(), "password is present");
		validationMessage.ifPresent(msg -> errorMessages.put("password_present", msg));
		validationMessage = ValidateUtils.validatePresent(user.getStatus(), "status is present");
		validationMessage.ifPresent(msg -> errorMessages.put("status_present", msg));

		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		return this.userRepository.update(user);
	}

	//	@Override
	//	public boolean updatePassword(UpdatePasswordRequest request) {
	//
	//		String currentPassword = request.getCurrentPassword();
	//		String newPassword = request.getNewPassword();
	//
	//		if (newPassword.length() < 8
	//				|| newPassword.length() < 20) {
	//			throw new Error();
	//		}
	//
	//		return userRepository.updatePassword(request);
	//	}

	@Override
	public boolean delete(Long userId) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(userId, "userId is null or empty");
		validationMessage.ifPresent(msg -> errorMessages.put("userId_nullOrEmpty", msg));
		validationMessage = ValidateUtils.validateZeroOrNegative(userId, "userIdis zero or negative");
		validationMessage.ifPresent(msg -> errorMessages.put("userId_ZeroOrNegative", msg));

		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		Optional<User> savedUser = findById(userId);
		if (!savedUser.isPresent()) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_USER_CODE);
		}

		return userRepository.delete(userId);
	}

	@Override
	public Optional<User> findByUserNameOrEmail(String usernameOrEmail) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(usernameOrEmail, "usernameOrEmail is null or empty");
		validationMessage.ifPresent(msg -> errorMessages.put("usernameOrEmail_nullOrEmpty", msg));

		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		Optional<User> user = userRepository.findUserByNameOrEmail(usernameOrEmail);
		if (!user.isPresent()) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_USER_CODE);
		}
		return user;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(email, "email is null or empty");
		validationMessage.ifPresent(msg -> errorMessages.put("email_nullOrEmpty", msg));

		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByName(String name) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(name, "name is null or empty");
		validationMessage.ifPresent(msg -> errorMessages.put("name_nullOrEmpty", msg));

		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		return userRepository.findByName(name);
	}

}
