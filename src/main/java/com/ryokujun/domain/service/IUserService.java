package com.ryokujun.domain.service;

import java.util.Collection;
import java.util.Optional;

import com.ryokujun.domain.entity.User;

public interface IUserService {

	public Optional<User> findById(int userId);

	public Collection<User> findAll();

	public boolean update(User user);

	public boolean delete(int userId);

	public boolean create(User user);

	public Optional<User> findByEmail(String email);

	public Optional<User> findByName(String name);

}
