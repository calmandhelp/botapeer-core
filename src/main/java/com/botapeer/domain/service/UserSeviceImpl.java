package com.botapeer.domain.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.botapeer.controller.payload.user.UpdatePasswordRequest;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.repository.IPlantRecordRepository;
import com.botapeer.domain.repository.IUserRepository;
import com.botapeer.domain.service.interfaces.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSeviceImpl implements IUserService {

	private final PasswordEncoder passwordEncoder;

	private final IUserRepository userRepository;
	private final IPlantRecordRepository plantRecordRepository;

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

		return userRepository.updatePassword(request);
	}

	@Override
	public boolean delete(Long userId) {
		return userRepository.delete(userId);
	}

	@Override
	public boolean create(User user) {
		return userRepository.create(user);
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
