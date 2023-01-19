package com.botapeer.infrastructure.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.botapeer.domain.entity.User;
import com.botapeer.domain.repository.IUserRepository;
import com.botapeer.infrastructure.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements IUserRepository {

	private final UserMapper userMapper;

	@Override
	public Optional<User> findById(Long userId) {
		return this.userMapper.findById(userId);
	}

	@Override
	public Collection<User> findUsers(String name) {
		return this.userMapper.findUsers(name);
	}

	@Override
	public boolean update(User user) {
		return this.userMapper.update(user);
	}

	@Override
	public boolean delete(Long userId) {
		return this.userMapper.delete(userId);
	}

	@Override
	public boolean create(User user) {
		return this.userMapper.create(user);
	}

	public Optional<User> findUserByNameOrEmail(String usernameOrEmail) {
		return this.userMapper.findUserByNameOrEmail(usernameOrEmail);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return this.userMapper.findByEmail(email);
	}

	@Override
	public Optional<User> findByName(String name) {
		return this.userMapper.findByName(name);
	}

}
