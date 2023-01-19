package com.ryokujun.domain.repository;

import java.util.Collection;
import java.util.Optional;

import com.ryokujun.domain.entity.User;

public interface IUserRepository {
	public Optional<User> findById(Long userId);

	public Collection<User> findUsers(String name);

	public boolean update(User user);

	public boolean delete(Long userId);

	public boolean create(User user);

	public Optional<User> findUserByNameOrEmail(String usernameOrEmail);

	public Optional<User> findByEmail(String email);

	public Optional<User> findByName(String name);

}
