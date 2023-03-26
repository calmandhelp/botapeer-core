package com.botapeer.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;
import com.botapeer.domain.repository.IUserRepository;

public class UserServiceImplTests {

	@InjectMocks
	private UserSeviceImpl userService;

	@Mock
	private IUserRepository userRepository;

	@BeforeEach
	void setup() {
		
		Collection<User> users = new ArrayList<User>();
		users.add(new User(1, new UserName("taro"), "taro@taro.com", new Password("encryptedPassword"),
				Integer.valueOf(1), "説明1",
				"", ""));
		users.add(new User(2, new UserName("jiro"), "jiro@taro.com", new Password("encryptedPassword"),
				Integer.valueOf(1), "説明2",
				"", ""));
		users.add(new User(3, new UserName("saburo"), "saburo@saburo.com", new Password("encryptedPassword"),
				Integer.valueOf(1), "説明3",
				"", ""));

		MockitoAnnotations.openMocks(this);
		
		for (User user : users) {
			if (user.getId().equals(1)) {
				Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
			}
			if (user.getId().equals(2)) {
				Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user));
			}
			if (user.getId().equals(3)) {
				Mockito.when(userRepository.findById(3L)).thenReturn(Optional.of(user));
			}
		}

		Mockito.when(userRepository.findUsers(Mockito.anyString())).thenReturn(users);
		for(User user: users) {
			if(user.getName().getName().equals("taro")) {
				Mockito.when(userRepository.findByName("taro")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUsers("taro")).thenReturn(Collections.singletonList(user));
			}else if(user.getName().getName().equals("jiro")) {
				Mockito.when(userRepository.findByName("jiro")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUsers("jiro")).thenReturn(Collections.singletonList(user));
			}else if(user.getName().getName().equals("saburo")) {
				Mockito.when(userRepository.findByName("saburo")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUsers("saburo")).thenReturn(Collections.singletonList(user));
			} 
		}
		
		Mockito.when(userRepository.create(Mockito.any(), Mockito.anyString())).thenReturn(4);
		
		userService = new UserSeviceImpl(userRepository);
	}

	@Test
	void testFindById() {
		User user = userService.findById(1L).orElse(null);
		Assertions.assertNotNull(user);
		Assertions.assertEquals(1, user.getId());
		Assertions.assertEquals("taro", user.getName().getName());
		Assertions.assertEquals("taro@taro.com", user.getEmail());
		Assertions.assertEquals("encryptedPassword", user.getPassword().getPassword());
		Assertions.assertEquals(1, user.getStatus());
		Assertions.assertEquals("説明1", user.getDescription());
		Assertions.assertEquals("", user.getProfileImage());
		Assertions.assertEquals("", user.getCoverImage());
	}

	@Test
	void testFindUsers() {
		Collection<User> user = userService.findUsers("test");
		Assertions.assertEquals(3, user.size());
		Collection<User> u = userService.findUsers("taro");
		Assertions.assertEquals(1, u.stream().findFirst().get().getId());
		Assertions.assertEquals("taro", u.stream().findFirst().get().getName().getName());
		Assertions.assertEquals("taro@taro.com", u.stream().findFirst().get().getEmail());
		Assertions.assertEquals("encryptedPassword", u.stream().findFirst().get().getPassword().getPassword());
		Assertions.assertEquals(1, u.stream().findFirst().get().getStatus());
		Assertions.assertEquals("", u.stream().findFirst().get().getProfileImage());
		Assertions.assertEquals("", u.stream().findFirst().get().getCoverImage());
	}

	@Test
	void testCreateUser() {
		User user = new User(new UserName("shiro"), "shiro@shiro.com", "説明4",
				"", "");
		user.setPassword(new Password("encryptedPassword"));
		Integer createdId = userService.create(user, "encryptedPassword");
		Assertions.assertEquals(4, createdId);
		Assertions.assertEquals(2, user.getStatus());
	}

}
