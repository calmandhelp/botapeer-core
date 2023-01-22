package com.botapeer.infrastructure.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.botapeer.controller.payload.user.UpdatePasswordRequest;
import com.botapeer.domain.entity.UserEntity;
import com.botapeer.domain.model.User;
import com.botapeer.domain.repository.IUserRepository;
import com.botapeer.infrastructure.mapper.UserMapper;
import com.botapeer.infrastructure.repository.dto.UserRepositoryDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements IUserRepository {

	private final UserMapper userMapper;

	@Override
	public Optional<User> findById(Long userId) {
		Optional<UserEntity> user = this.userMapper.findById(userId);
		return UserRepositoryDto.toModel(user);
	}

	@Override
	public Collection<User> findUsers(String name) {
		Collection<UserEntity> user = this.userMapper.findUsers(name);
		return UserRepositoryDto.toModel(user);
	}

	@Override
	public boolean update(User user) {
		UserEntity userEntity = UserRepositoryDto.toEntity(user);
		return this.userMapper.update(userEntity);
	}

	@Override
	public boolean updatePassword(UpdatePasswordRequest request) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean delete(Long userId) {
		return this.userMapper.delete(userId);
	}

	@Override
	public boolean create(User user) {
		UserEntity u = UserRepositoryDto.toEntity(user);
		return this.userMapper.create(u);
	}

	public Optional<User> findUserByNameOrEmail(String usernameOrEmail) {
		Optional<UserEntity> user = this.userMapper.findUserByNameOrEmail(usernameOrEmail);
		return UserRepositoryDto.toModel(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Optional<UserEntity> user = this.userMapper.findByEmail(email);
		return UserRepositoryDto.toModel(user);
	}

	@Override
	public Optional<User> findByName(String name) {
		Optional<UserEntity> user = this.userMapper.findByName(name);
		return UserRepositoryDto.toModel(user);
	}

}
