package com.botapeer.domain.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.botapeer.domain.model.user.User;
import com.botapeer.domain.repository.IUserRepository;
import com.botapeer.domain.service.interfaces.IUserService;
import com.botapeer.util.ValidateUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private final IUserRepository userRepository;

	@Override
	public Optional<User> findById(Long userId) {
		if (userId == null) {
			throw new NullPointerException();
		}
		if (userId < 0) {
			throw new IllegalArgumentException();
		}
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
		ValidateUtils.validNullOrEmpty(encryptedPassword);
		user.setStatus(2);
		return this.userRepository.create(user, encryptedPassword);
	}

	@Override
	public boolean update(User user) {
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
		return userRepository.delete(userId);
	}

	@Override
	public Optional<User> findByUserNameOrEmail(String usernameOrEmail) {
		return userRepository.findUserByNameOrEmail(usernameOrEmail);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByName(String name) {
		return userRepository.findByName(name);
	}

}
