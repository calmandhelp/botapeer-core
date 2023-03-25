package com.botapeer.domain.repository.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;
import com.botapeer.domain.repository.IUserRepository;

public class UserRepositoryMock implements IUserRepository {

	private List<User> users = new ArrayList<>();

	public UserRepositoryMock() {
		users.add(new User(Integer.valueOf(1), new UserName("taro"), "taro@taro.com", new Password("password"),
				Integer.valueOf(1), "説明",
				"", ""));
		users.add(new User(Integer.valueOf(2), new UserName("jiro"), "jiro@taro.com", new Password("password"),
				Integer.valueOf(1), "説明",
				"", ""));
		users.add(new User(Integer.valueOf(3), new UserName("saburo"), "saburo@saburo.com", new Password("password"),
				Integer.valueOf(1), "説明",
				"", ""));
	}

	@Override
	public Optional<User> findById(Long userId) {
		return users.stream()
				.filter(user -> user.getId().equals(userId))
				.findFirst();
	}

	@Override
	public Collection<User> findUsers(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Integer create(User user, String encryptedPassword) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean update(User user) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean delete(Long userId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Optional<User> findUserByNameOrEmail(String usernameOrEmail) {
		// TODO 自動生成されたメソッド・スタブ
		return Optional.empty();
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO 自動生成されたメソッド・スタブ
		return Optional.empty();
	}

	@Override
	public Optional<User> findByName(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return Optional.empty();
	}
}
