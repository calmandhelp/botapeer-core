package com.ryokujun.domain.repository;

import java.util.Collection;

import com.ryokujun.domain.entity.User;

public interface IUserRepository {
	public User findById(int userId);

	public Collection<User> findAll();

	public boolean update(User user);

	public boolean delete(int userId);

	public boolean create(User user);

	public User findByEmail(String email);
}
