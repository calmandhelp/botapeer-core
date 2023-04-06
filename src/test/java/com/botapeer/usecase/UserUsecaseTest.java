package com.botapeer.usecase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;

import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;
import com.botapeer.domain.service.interfaces.IPlantRecordService;
import com.botapeer.domain.service.interfaces.IUserService;
import com.botapeer.exception.NotFoundException;

import model.CreateUserRequest;
import model.UserResponse;

public class UserUsecaseTest {

	@InjectMocks
	private UserUsecase userUsecase;

	@Mock
	private IUserService userService;

	@Mock
	private Validator validator;

	@Mock
	private Logger logger;

	@Mock
	private IPlantRecordService plantRecordService;

	@Mock
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	@BeforeEach
	void setup() throws IOException {
		Collection<User> userList= new ArrayList<>();
		User user1 = new User(1, new UserName("taro"),"taro@taro.com",new Password("encryptedPassword"), 1, "説明1","/images/imagePath1","/images/imagePath2");	
		User user2 = new User(2, new UserName("jiro"), "jiro@jiro.com", new Password("encryptedPassword"), 1, "説明2", "/images/imagePath1", "/images/imagePath2");
		User user3 = new User(1, new UserName("saburo"),"saburo@saburo.com",new Password("encryptedPassword"), 1, "説明3","/images/imagePath1","/images/imagePath2");
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		
		MockitoAnnotations.openMocks(this);

		Mockito.when(userService.findById(1L)).thenReturn(userList.stream().findFirst());
		Mockito.when(userService.findById(2L)).thenReturn(userList.stream().findFirst());
		Mockito.when(userService.findById(3L)).thenReturn(userList.stream().findFirst());
		
		Mockito.when(userService.findUsers(Mockito.any())).thenReturn(new ArrayList<>());
		Mockito.when(userService.findUsers("")).thenReturn(userList);
		Mockito.when(userService.create(Mockito.any(), Mockito.any()))
		.thenAnswer(invocation -> {
			User createdUser = invocation.getArgument(0);
			createdUser.setId(userList.size() + 1);
			userList.add(createdUser);
			Mockito.when(userService.findById((long)userList.size())).thenReturn(Optional.of(createdUser));
			return userList.size();
		});
		
		for(User user: userList) {
			if(user.getId().equals(1)) {
				Mockito.when(userService.findUsers(user.getName().getName())).thenReturn(Collections.singletonList(user));
			}
			if(user.getId().equals(2)) {
				Mockito.when(userService.findUsers(user.getName().getName())).thenReturn(Collections.singletonList(user));
			}
			if(user.getId().equals(3)) {
				Mockito.when(userService.findUsers(user.getName().getName())).thenReturn(Collections.singletonList(user));
			}
		}
		
		userUsecase = new UserUsecase(userService, null, passwordEncoder, validator);
	}

	@Test
	void findByIdTest() {
		UserResponse user = userUsecase.findById("1").orElse(null);
		Assertions.assertNotNull(user);
		Assertions.assertEquals(1, user.getId());
		Assertions.assertEquals("taro", user.getName());
		Assertions.assertEquals("taro@taro.com", user.getEmail());
		Assertions.assertEquals(1, user.getStatus());
		Assertions.assertEquals("説明1", user.getDescription());
		Assertions.assertEquals("/images/imagePath1", user.getProfileImage());
		Assertions.assertEquals("/images/imagePath2", user.getCoverImage());
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.findById("-1");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.findById(null);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.findById("test");
		});
		Assertions.assertThrows(NotFoundException.class, () -> {
			userUsecase.findById("100");
		});
	}

	@Test
	void findUsersTest() {
		Collection<UserResponse> userWithOne = userUsecase.findUsers("taro");
		Assertions.assertEquals(1, userWithOne.stream().findFirst().get().getId());
		Assertions.assertEquals("taro", userWithOne.stream().findFirst().get().getName());
		Assertions.assertEquals("taro@taro.com", userWithOne.stream().findFirst().get().getEmail());
		Assertions.assertEquals(1, userWithOne.stream().findFirst().get().getStatus());
		Assertions.assertEquals("説明1", userWithOne.stream().findFirst().get().getDescription());
		Assertions.assertEquals("/images/imagePath1", userWithOne.stream().findFirst().get().getProfileImage());
		Assertions.assertEquals("/images/imagePath2", userWithOne.stream().findFirst().get().getCoverImage());

		Collection<UserResponse> userWithNoUser = userUsecase.findUsers("test");
		Assertions.assertEquals(0, userWithNoUser.size());

		Collection<UserResponse> userWithNullUser = userUsecase.findUsers(null);
		Assertions.assertEquals(3, userWithNullUser.size());

		Collection<UserResponse> userWithEmptyUser = userUsecase.findUsers("");
		Assertions.assertEquals(3, userWithEmptyUser.size());
	}

	@Test
	void create() {
		Optional<UserResponse> userWithSuccess = userUsecase
				.create(new CreateUserRequest("taro", "taro@taro.com", "password"));

		Assertions.assertEquals(4, userWithSuccess.get().getId());
		Assertions.assertEquals("taro", userWithSuccess.get().getName());
		Assertions.assertEquals("taro@taro.com", userWithSuccess.get().getEmail());

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.create(null);
		});

		CreateUserRequest userWithNullName = new CreateUserRequest(null, "taro@taro.com", "password");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.create(userWithNullName);
		});

		CreateUserRequest userWithEmptyName = new CreateUserRequest("", "taro@taro.com", "password");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.create(userWithEmptyName);
		});

		CreateUserRequest userWith15OverName = new CreateUserRequest("abcdefghijklmnop", "taro@taro.com", "password");
		setValidation(userWith15OverName);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userUsecase.create(userWith15OverName);
		});

		CreateUserRequest userWithNullEmail = new CreateUserRequest("taro", null, "password");
		setValidation(userWithNullEmail);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userUsecase.create(userWithNullEmail);
		});

		CreateUserRequest userWithEmptyEmail = new CreateUserRequest("taro", "", "password");
		setValidation(userWithEmptyEmail);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userUsecase.create(userWithEmptyEmail);
		});

		CreateUserRequest userWithNullPassword = new CreateUserRequest("taro", "taro@taro.com", null);
		setValidation(userWithNullPassword);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userUsecase.create(userWithNullPassword);
		});

		CreateUserRequest userWithEmptyPassword = new CreateUserRequest("taro", "taro@taro.com", "");
		setValidation(userWithEmptyPassword);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userUsecase.create(userWithEmptyPassword);
		});

		CreateUserRequest userWithUnder8Password = new CreateUserRequest("taro", "taro@taro.com", "8UnderP");
		setValidation(userWithUnder8Password);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userUsecase.create(userWithUnder8Password);
		});

		CreateUserRequest userWithOver20Password = new CreateUserRequest("taro", "taro@taro.com", "20OverPasswordValidation");
		setValidation(userWithOver20Password);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userUsecase.create(userWithOver20Password);
		});

		User userWithEncryptedPassword = new User();
		userWithEncryptedPassword.setPassword(new Password("password"));
		Password password = new Password("password");
		String encryptedPassword = passwordEncoder.encode(password.getPassword());
		int userId = userService.create(userWithEncryptedPassword, encryptedPassword);
		Assertions.assertEquals(5, userId);
	}

	@Test
	void update() {

//		UpdateUserFormData updateUserFormData = new UpdateUserFormData("taro", "taro@taro.com");
//		updateUserFormData.setDescription("説明1");
//
//		userUsecase.update(updateUserFormData, mockMultipartFileProfileImage,mockMultipartFileCoverImage, "1");

	}

	@Test
	void delete() {

	}

	void setValidation(CreateUserRequest request) {
		if (ObjectUtils.isEmpty(request.getEmail())) {
			Mockito.when(validator.validate(request)).thenThrow(ConstraintViolationException.class);
		}
		if(!ObjectUtils.isEmpty(request.getName()) && request.getName().length() > 15) {
			Mockito.when(validator.validate(request)).thenThrow(ConstraintViolationException.class);
		}
		if(ObjectUtils.isEmpty(request.getPassword()) || request.getPassword().length() < 8 || request.getPassword().length() > 20) {
			Mockito.when(validator.validate(request)).thenThrow(ConstraintViolationException.class);
		}
	}

}
