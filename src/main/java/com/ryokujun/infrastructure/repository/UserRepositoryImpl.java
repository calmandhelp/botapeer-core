package com.ryokujun.infrastructure.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ryokujun.domain.entity.User;
import com.ryokujun.domain.repository.IUserRepository;
import com.ryokujun.infrastructure.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements IUserRepository {

	private final UserMapper userMapper;

	@Override
	public Optional<User> findById(int userId) {
		return this.userMapper.findById(userId);
	}

	@Override
	public Collection<User> findAll() {
		return this.userMapper.findAll();
	}

	@Override
	public boolean update(User user) {
		return this.userMapper.update(user);
	}

	@Override
	public boolean delete(int userId) {
		return this.userMapper.delete(userId);
	}

	@Override
	public boolean create(User user) {
		return this.userMapper.create(user);
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
