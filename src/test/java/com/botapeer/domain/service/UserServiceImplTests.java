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
	private UserServiceImpl userService;

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

		Mockito.when(userRepository.findUsers(Mockito.anyString())).thenReturn(new ArrayList<User>());
		Mockito.when(userRepository.findUsers("")).thenReturn(users);
		for(User user: users) {
			if (user.getId().equals(1)) {
				Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
			} else if (user.getId().equals(2)) {
				Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user));
			} else if(user.getId().equals(3)) {
				Mockito.when(userRepository.findById(3L)).thenReturn(Optional.of(user));
			}
			if(user.getName().getName().equals("taro")) {
				Mockito.when(userRepository.findByName("taro")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUsers("taro")).thenReturn(Collections.singletonList(user));
				Mockito.when(userRepository.findUserByNameOrEmail("taro")).thenReturn(Optional.of(user));
			}else if(user.getName().getName().equals("jiro")) {
				Mockito.when(userRepository.findByName("jiro")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUsers("jiro")).thenReturn(Collections.singletonList(user));
				Mockito.when(userRepository.findUserByNameOrEmail("jiro")).thenReturn(Optional.of(user));
			}else if(user.getName().getName().equals("saburo")) {
				Mockito.when(userRepository.findByName("saburo")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUsers("saburo")).thenReturn(Collections.singletonList(user));
				Mockito.when(userRepository.findUserByNameOrEmail("saburo")).thenReturn(Optional.of(user));
			} 
		}
		
		Mockito.when(userRepository.create(Mockito.isA(User.class), Mockito.anyString())).thenReturn(4);
		Mockito.when(userRepository.update(Mockito.isA(User.class))).thenReturn(true);
		Mockito.when(userRepository.delete(Mockito.anyLong())).thenReturn(true);
		
		
		userService = new UserServiceImpl(userRepository);
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
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.findById(-1L);
		});
		Assertions.assertThrows(NullPointerException.class, () -> {
			userService.findById(null);
		});
		Optional<User> nullUser = userService.findById(100L);
		Assertions.assertEquals(Optional.empty(), nullUser);
	}

	@Test
	void testFindUsers() {
		Collection<User> u = userService.findUsers("taro");
		Assertions.assertEquals(1, u.stream().findFirst().get().getId());
		Assertions.assertEquals("taro", u.stream().findFirst().get().getName().getName());
		Assertions.assertEquals("taro@taro.com", u.stream().findFirst().get().getEmail());
		Assertions.assertEquals("encryptedPassword", u.stream().findFirst().get().getPassword().getPassword());
		Assertions.assertEquals(1, u.stream().findFirst().get().getStatus());
		Assertions.assertEquals("", u.stream().findFirst().get().getProfileImage());
		Assertions.assertEquals("", u.stream().findFirst().get().getCoverImage());
		Assertions.assertThrows(NullPointerException.class, () -> {
			userService.findUsers(null);
		});
		Collection<User> userEmpty = userService.findUsers("");
		Assertions.assertEquals(3, userEmpty.size());
		Collection<User> userTest = userService.findUsers("test");
		Assertions.assertEquals(0, userTest.size());
	}

	@Test
	void testCreateUser() {
		User user = new User(new UserName("shiro"), "shiro@shiro.com", "説明4",
				"", "");
		Integer createdId = userService.create(user, "encryptedPassword");
		Assertions.assertEquals(4, createdId);
		Assertions.assertEquals(2, user.getStatus());
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.create(user, "");
		});
		Assertions.assertThrows(NullPointerException.class, () -> {
			userService.create(user, null);
		});
	}

	@Test
	void testUpdateUser() {
		User user = new User(1, new UserName("goro"), "goro@goro.com", "説明5",
				"/image/imagePath1", "/image/imagePath2");
		boolean isSuccess = userService.update(user);
		Assertions.assertEquals(isSuccess, true);
	}

	@Test
	void testDelete() {
		boolean isSuccess = userService.delete(1L);
		Assertions.assertEquals(isSuccess, true);
	}

	@Test
	void testFindUserNameOrEmail() {
		Optional<User> user = userService.findByUserNameOrEmail("test@test.com");
	}

}
