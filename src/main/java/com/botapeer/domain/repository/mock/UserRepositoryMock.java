package com.botapeer.domain.repository.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;
import com.botapeer.domain.repository.IUserRepository;

public class UserRepositoryMock implements IUserRepository {

	private Collection<User> users = new ArrayList<>();

	public UserRepositoryMock() {
		users.add(new User(1, new UserName("taro"), "taro@taro.com", new Password("encryptedPassword"),
				Integer.valueOf(1), "説明1",
				"", ""));
		users.add(new User(2, new UserName("jiro"), "jiro@taro.com", new Password("encryptedPassword"),
				Integer.valueOf(1), "説明2",
				"", ""));
		users.add(new User(3, new UserName("saburo"), "saburo@saburo.com", new Password("encryptedPassword"),
				Integer.valueOf(1), "説明3",
				"", ""));
	}

	@Override
	public Optional<User> findById(Long userId) {
		return users.stream()
				.filter(user -> user.getId().equals(Integer.valueOf(userId.intValue())))
				.findFirst();
	}

	@Override
	public Collection<User> findUsers(String name) {
		Collection<User> u = new ArrayList<>();
		Optional<User> optionalUser = users.stream()
				.filter(user -> user.getName().getName().equals(name))
				.findFirst();
		if (optionalUser.isPresent()) {
			u.add(optionalUser.get());
			return u;
		} else {
			return (Collection<User>) users;
		}
	}

	@Override
	public Integer create(User user, String encryptedPassword) {
		if (user.getName().getName().isEmpty() ||
				user.getEmail().isEmpty() ||
				user.getPassword().getPassword().isEmpty()) {
			return null;
		}
		users.add(new User(4, user.getName(), user.getEmail(), new Password(encryptedPassword),
				user.getStatus(), user.getDescription(),
				user.getProfileImage(), user.getCoverImage()));
		return (int) (users.size() + 1.);
	}

	@Override
	public boolean update(User user) {
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
