package com.botapeer.domain.repository;

import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.user.User;

public interface IUserRepository {
	public Optional<User> findById(Long userId);

	public Collection<User> findUsers(String name);

	public Integer create(User user, String encryptedPassword);

	public boolean update(User user);

	//	public boolean updatePassword(UpdateUserRequest request);

	public boolean delete(Long userId);

	public Optional<User> findUserByNameOrEmail(String usernameOrEmail);

	public Optional<User> findByEmail(String email);

	public Optional<User> findByName(String name);
}
