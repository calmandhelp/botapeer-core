package com.botapeer.domain.service;

import java.util.Collection;
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
		ValidateUtils.validateNullOrEmpty(userId);
		ValidateUtils.validateZeroOrNegative(userId);
		return this.userRepository.findById(userId);
	}

	@Override
	public Collection<User> findUsers(String name) {
		ValidateUtils.validateNull(name);
		return this.userRepository.findUsers(name);
	}

	@Override
	public Integer create(User user, String encryptedPassword) {
		ValidateUtils.validateNullOrEmpty(encryptedPassword);
		user.setStatus(2);
		return this.userRepository.create(user, encryptedPassword);
	}

	@Override
	public boolean update(User user) {
		ValidateUtils.validateNullOrEmpty(user);
		Optional<User> savedUser = findById((long) user.getId());
		if (!savedUser.isPresent()) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_USER_CODE);
		}
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
		ValidateUtils.validatePresent(user.getPassword(), user.getStatus());

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
		ValidateUtils.validateNullOrEmpty(userId);
		ValidateUtils.validateZeroOrNegative(userId);
		return userRepository.delete(userId);
	}

	@Override
	public Optional<User> findByUserNameOrEmail(String usernameOrEmail) {
		ValidateUtils.validateNullOrEmpty(usernameOrEmail);
		Optional<User> user = userRepository.findUserByNameOrEmail(usernameOrEmail);
		if (!user.isPresent()) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_USER_CODE);
		}
		return user;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		ValidateUtils.validateNullOrEmpty(email);
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByName(String name) {
		ValidateUtils.validateNullOrEmpty(name);
		return userRepository.findByName(name);
	}

	void validateNullOrEmpty() {

	}

}
