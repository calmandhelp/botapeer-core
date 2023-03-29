package com.botapeer.domain.service;

import java.util.Collection;
import java.util.Optional;

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

	private final IUserRepository userRepository;

	@Override
	public Optional<User> findById(Long userId) {
		ValidateUtils.validateNullOrEmpty(userId);
		ValidateUtils.validateZeroOrNegative(userId);
		return this.userRepository.findById(userId);
	}

	@Override
	public Collection<User> findUsers(String name) {
		if (name == null) {
			throw new NullPointerException();
		}
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
		ValidateUtils.validateNotEmpty(user.getPassword(), user.getStatus());
		if (!findById((long) user.getId()).isPresent()) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_USER_CODE);
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

}
