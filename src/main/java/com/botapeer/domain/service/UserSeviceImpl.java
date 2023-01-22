package com.botapeer.domain.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.botapeer.controller.payload.user.UpdatePasswordRequest;
import com.botapeer.domain.model.User;
import com.botapeer.domain.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSeviceImpl implements IUserService {

	private final PasswordEncoder passwordEncoder;

	private final IUserRepository userRepository;

	@Override
	public Optional<User> findById(Long userId) {
		return this.userRepository.findById(userId);
	}

	@Override
	public Collection<User> findUsers(String name) {
		return this.userRepository.findUsers(name);
	}

	@Override
	public boolean update(User user) {
		return this.userRepository.update(user);
	}

	@Override
	public boolean updatePassword(UpdatePasswordRequest request) {

		String currentPassword = request.getCurrentPassword();
		String newPassword = request.getNewPassword();

		if (newPassword.length() < 8
				|| newPassword.length() < 20) {
			throw new Error();
		}

		return this.userRepository.updatePassword(request);
	}

	@Override
	public boolean delete(Long userId) {
		return this.userRepository.delete(userId);
	}

	@Override
	public boolean create(User user) {
		return this.userRepository.create(user);
	}

	@Override
	public Optional<User> findByUserNameOrEmail(String usernameOrEmail) {
		return this.userRepository.findUserByNameOrEmail(usernameOrEmail);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByName(String name) {
		return this.userRepository.findByName(name);
	}

}
