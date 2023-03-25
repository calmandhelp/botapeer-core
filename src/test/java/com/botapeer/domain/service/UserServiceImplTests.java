package com.botapeer.domain.service;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.botapeer.domain.model.user.User;
import com.botapeer.domain.repository.mock.UserRepositoryMock;

public class UserServiceImplTests {

	@Test
	void testFindById() {
		UserRepositoryMock userRepository = new UserRepositoryMock();
		User user = userRepository.findById(2L).orElse(null);
		Assertions.assertNotNull(user);
		Assertions.assertEquals(2, user.getId());
		Assertions.assertEquals("jiro", user.getName().getName());
		Assertions.assertEquals("password", user.getPassword().getPassword());
		Assertions.assertEquals(1, user.getStatus());
		Assertions.assertEquals("説明2", user.getDescription());
		Assertions.assertEquals("", user.getProfileImage());
		Assertions.assertEquals("", user.getCoverImage());
	}

	@Test
	void testFindUsers() {
		UserRepositoryMock userRepository = new UserRepositoryMock();
		Collection<User> user = userRepository.findUsers("test");
		Assertions.assertEquals(3, user.size());
		Collection<User> u = userRepository.findUsers("taro");
		Assertions.assertEquals(1, u.stream().findFirst().get().getId());
		Assertions.assertEquals("taro", u.stream().findFirst().get().getName().getName());
		Assertions.assertEquals("password", u.stream().findFirst().get().getPassword().getPassword());
		Assertions.assertEquals(1, u.stream().findFirst().get().getStatus());
		Assertions.assertEquals("", u.stream().findFirst().get().getProfileImage());
		Assertions.assertEquals("", u.stream().findFirst().get().getCoverImage());
	}

}
