package com.botapeer.usecase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.botapeer.constants.ResponseConstants;
import com.botapeer.domain.model.plantRecord.PlantRecord;
import model.UpdateUserFormData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;
import com.botapeer.domain.service.interfaces.IPlantRecordService;
import com.botapeer.domain.service.interfaces.IUserService;
import com.botapeer.exception.NotFoundException;

import model.CreateUserRequest;
import model.UserResponse;
import org.springframework.web.multipart.MultipartFile;

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

		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);

		Authentication auth = Mockito.mock(Authentication.class);
		UserDetails userDetails = Mockito.mock(UserDetails.class);

		Mockito.when(securityContext.getAuthentication()).thenReturn(auth);


		Collection<User> userList = new ArrayList<>();
		User user1 = new User(1, new UserName("taro"),"taro@taro.com",new Password("encryptedPassword"), 1, "説明1","/images/imagePath1","/images/imagePath2");	
		User user2 = new User(2, new UserName("jiro"), "jiro@jiro.com", new Password("encryptedPassword"), 1, "説明2", "/images/imagePath1", "/images/imagePath2");
		User user3 = new User(1, new UserName("saburo"),"saburo@saburo.com",new Password("encryptedPassword"), 1, "説明3","/images/imagePath1","/images/imagePath2");
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);

		Collection<PlantRecord> plantRecordList = new ArrayList<>();
		for(int i = 1; i <= 3; i++) {
			PlantRecord plantRecord = new PlantRecord();
			plantRecord.setId((long)i);
			plantRecord.setUserId(i);
			plantRecordList.add(plantRecord);
		}

		MockitoAnnotations.openMocks(this);

		for(PlantRecord plantRecord: plantRecordList) {
			Mockito.when(plantRecordService.findById(plantRecord.getId())).thenReturn(Optional.of(plantRecord));
		}

		Mockito.when(userService.findById(1L)).thenReturn(userList.stream().findFirst());
		Mockito.when(userService.findById(2L)).thenReturn(userList.stream().findFirst());
		Mockito.when(userService.findById(3L)).thenReturn(userList.stream().findFirst());
		
		Mockito.when(userService.findUsers(Mockito.any())).thenReturn(new ArrayList<>());
		Mockito.when(userService.findUsers("")).thenReturn(userList);
		Mockito.when(userService.findUsers(null)).thenReturn(userList);
		Mockito.when(userService.create(Mockito.any(), Mockito.any()))
		.thenAnswer(invocation -> {
			User createdUser = invocation.getArgument(0);
			createdUser.setId(userList.size() + 1);
			userList.add(createdUser);
			Mockito.when(userService.findById((long)userList.size())).thenReturn(Optional.of(createdUser));
			return userList.size();
		});
		
		for(User user: userList) {
			Mockito.when(auth.getName()).thenReturn(user.getName().getName());
			Mockito.when(userService.findByName(user.getName().getName())).thenReturn(Optional.of(user));
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

		Mockito.when(userService.update(Mockito.isA(User.class), Mockito.isA(MultipartFile.class) , Mockito.isA(MultipartFile.class)))
		.thenAnswer(invocation -> {
			User userWithUpdateArgument = invocation.getArgument(0);
			for(User userInMockList : userList) {
				if (userWithUpdateArgument.getId().equals(userInMockList.getId())) {
					userInMockList.setName(userWithUpdateArgument.getName());
					userInMockList.setPassword(userWithUpdateArgument.getPassword());
					userInMockList.setEmail(userWithUpdateArgument.getEmail());
					userInMockList.setStatus(userWithUpdateArgument.getStatus());
					if(!ObjectUtils.isEmpty(userWithUpdateArgument.getProfileImage())) {
						userInMockList.setProfileImage(userWithUpdateArgument.getProfileImage());
					}
					if(!ObjectUtils.isEmpty(userWithUpdateArgument.getCoverImage())) {
						userInMockList.setCoverImage(userWithUpdateArgument.getCoverImage());
					}

					return Optional.of(userInMockList);
				}
			}
			throw new NotFoundException(ResponseConstants.NOTFOUND_USER_CODE);
		});

		userUsecase = new UserUsecase(userService, plantRecordService, passwordEncoder, validator);
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

		Assertions.assertTrue(userWithSuccess.isPresent());
		Assertions.assertEquals(4, userWithSuccess.get().getId());
		Assertions.assertEquals("taro", userWithSuccess.get().getName());
		Assertions.assertEquals("taro@taro.com", userWithSuccess.get().getEmail());
		Assertions.assertEquals("", userWithSuccess.get().getDescription());
		Assertions.assertEquals("", userWithSuccess.get().getProfileImage());
		Assertions.assertEquals("", userWithSuccess.get().getCoverImage());

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

//		CreateUserRequest userWith15OverName = new CreateUserRequest("abcdefghijklmnop", "taro@taro.com", "password");
//		setValidationCreate(userWith15OverName);
//		Assertions.assertThrows(ConstraintViolationException.class, () -> {
//			userUsecase.create(userWith15OverName);
//		});

//		CreateUserRequest userWithNullEmail = new CreateUserRequest("taro", null, "password");
//		setValidationCreate(userWithNullEmail);
//		Assertions.assertThrows(ConstraintViolationException.class, () -> {
//			userUsecase.create(userWithNullEmail);
//		});
//
//		CreateUserRequest userWithEmptyEmail = new CreateUserRequest("taro", "", "password");
//		setValidationCreate(userWithEmptyEmail);
//		Assertions.assertThrows(ConstraintViolationException.class, () -> {
//			userUsecase.create(userWithEmptyEmail);
//		});
//
//		CreateUserRequest userWithNullPassword = new CreateUserRequest("taro", "taro@taro.com", null);
//		setValidationCreate(userWithNullPassword);
//		Assertions.assertThrows(ConstraintViolationException.class, () -> {
//			userUsecase.create(userWithNullPassword);
//		});
//
//		CreateUserRequest userWithEmptyPassword = new CreateUserRequest("taro", "taro@taro.com", "");
//		setValidationCreate(userWithEmptyPassword);
//		Assertions.assertThrows(ConstraintViolationException.class, () -> {
//			userUsecase.create(userWithEmptyPassword);
//		});
//
//		CreateUserRequest userWithUnder8Password = new CreateUserRequest("taro", "taro@taro.com", "8UnderP");
//		setValidationCreate(userWithUnder8Password);
//		Assertions.assertThrows(ConstraintViolationException.class, () -> {
//			userUsecase.create(userWithUnder8Password);
//		});
//
//		CreateUserRequest userWithOver20Password = new CreateUserRequest("taro", "taro@taro.com", "20OverPasswordValidation");
//		setValidationCreate(userWithOver20Password);
//		Assertions.assertThrows(ConstraintViolationException.class, () -> {
//			userUsecase.create(userWithOver20Password);
//		});

		User userWithEncryptedPassword = new User();
		userWithEncryptedPassword.setPassword(new Password("password"));
		Password password = new Password("password");
		String encryptedPassword = passwordEncoder.encode(password.getPassword());
		int userId = userService.create(userWithEncryptedPassword, encryptedPassword);
		Assertions.assertEquals(5, userId);
	}

	@Test
	void update() {

//		画像更新用
		String contentProfile = "profile content";
		String fileNameProfile = "profile.jpg";
		String contentTypeProfile = "image/jpg";
		byte[] contentBytesProfile = contentProfile.getBytes(StandardCharsets.UTF_8);
		MultipartFile mockMultipartFileProfileImage = new MockMultipartFile(contentProfile, fileNameProfile, contentTypeProfile, contentBytesProfile);

		String contentCover = "cover content";
		String fileNameCover = "cover.jpg";
		String contentTypeCover = "image/jpg";
		byte[] contentBytesCover = contentCover.getBytes(StandardCharsets.UTF_8);
		MultipartFile mockMultipartFileCoverImage = new MockMultipartFile(contentCover, fileNameCover, contentTypeCover, contentBytesCover);

		UpdateUserFormData updateUserFormData = new UpdateUserFormData("goro", "goro@goro.com");
		updateUserFormData.setDescription("説明1");

		Optional<UserResponse> updatedUser = userUsecase.update(updateUserFormData, mockMultipartFileProfileImage, mockMultipartFileCoverImage, "1");

		Assertions.assertTrue(updatedUser.isPresent());
		Assertions.assertEquals("goro", updatedUser.get().getName());
		Assertions.assertEquals("goro@goro.com", updatedUser.get().getEmail());

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.update(null, mockMultipartFileProfileImage, mockMultipartFileCoverImage, "1");
		});

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.update(updateUserFormData, mockMultipartFileProfileImage, mockMultipartFileCoverImage, "-1");
		});

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.update(updateUserFormData, mockMultipartFileProfileImage, mockMultipartFileCoverImage, null);
		});

		UpdateUserFormData updateUserFormDataWithNullName = new UpdateUserFormData(null, "goro@goro.com");
		setValidationUpdateUserFormData(updateUserFormDataWithNullName);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userUsecase.update(updateUserFormDataWithNullName, mockMultipartFileProfileImage, mockMultipartFileCoverImage, "1");
		});

		UpdateUserFormData updateUserFormDataWithNullEmail = new UpdateUserFormData("goro", null);
		setValidationUpdateUserFormData(updateUserFormDataWithNullEmail);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userUsecase.update(updateUserFormDataWithNullEmail, mockMultipartFileProfileImage, mockMultipartFileCoverImage, "1");
		});


		UpdateUserFormData updateUserFormDataWithEmptyEmail = new UpdateUserFormData("goro", "");
		setValidationUpdateUserFormData(updateUserFormDataWithEmptyEmail);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userUsecase.update(updateUserFormDataWithEmptyEmail, mockMultipartFileProfileImage, mockMultipartFileCoverImage, "1");
		});

	}

	@Test
	void delete() {

	}

	@Test
	void findByPlantRecordId() {

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.findByPlantRecordId("");
		});

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.findByPlantRecordId(null);
		});

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.findByPlantRecordId("-10");
		});

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userUsecase.findByPlantRecordId("aaaaa");
		});

		Optional<UserResponse> userSuccess = userUsecase.findByPlantRecordId("1");
		Assertions.assertTrue(userSuccess.isPresent());
		Assertions.assertEquals(1 ,userSuccess.get().getId());
		Assertions.assertEquals("taro" ,userSuccess.get().getName());
		Assertions.assertEquals("taro@taro.com" ,userSuccess.get().getEmail());
	}

	void setValidationCreate(CreateUserRequest request) {
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

	void setValidationUpdateUserFormData(UpdateUserFormData request) {
		if (ObjectUtils.isEmpty(request.getEmail())) {
			Mockito.when(validator.validate(request)).thenThrow(ConstraintViolationException.class);
		}
		if(ObjectUtils.isEmpty(request.getName()) || request.getName().length() > 15) {
			Mockito.when(validator.validate(request)).thenThrow(ConstraintViolationException.class);
		}
	}

}
