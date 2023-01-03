package com.ryokujun.domain.service;

import java.util.Collection;
import java.util.Optional;

import com.ryokujun.domain.entity.User;

public interface IUserService {

	public Optional<User> findById(Long userId);

	public Collection<User> findAll();

	public boolean update(User user);

	public boolean delete(Long userId);

	public boolean create(User user);

	public Optional<User> findByUserNameOrEmail(String usernameOrEmail);

	public Optional<User> findByEmail(String email);

	public Optional<User> findByName(String name);

}
