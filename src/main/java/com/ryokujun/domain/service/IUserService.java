package com.ryokujun.domain.service;

import java.util.Collection;

import com.ryokujun.domain.entity.User;

public interface IUserService {

	public User findById(String userId);

	public Collection<User> findAll();

	public boolean update(User user);

	public boolean delete(int userId);

}
