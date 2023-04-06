package com.botapeer.domain.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.botapeer.domain.adapter.IUploader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ObjectUtils;

import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;
import com.botapeer.domain.repository.IUserRepository;
import com.botapeer.exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

public class UserServiceImplTests {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private IUserRepository userRepository;

	@Mock
	private Validator validator;

	@Mock
	private IUploader uploader;

	@BeforeEach
	void setup() throws IOException {
		Collection<User> users = new ArrayList<>();
		users.add(new User(1, new UserName("taro"), "taro@taro.com", new Password("encryptedPassword"),
				1, "説明1",
				"", ""));
		users.add(new User(2, new UserName("jiro"), "jiro@jiro.com", new Password("encryptedPassword"),
				1, "説明2",
				"", ""));
		users.add(new User(3, new UserName("saburo"), "saburo@saburo.com", new Password("encryptedPassword"),
				1, "説明3",
				"", ""));

		MockitoAnnotations.openMocks(this);

		Mockito.when(userRepository.findUsers(Mockito.anyString())).thenReturn(new ArrayList<User>());
		Mockito.when(userRepository.findUsers("")).thenReturn(users);
		Mockito.when(userRepository.delete(Mockito.anyLong())).thenReturn(false);
		for(User user: users) {
			if (user.getId().equals(1)) {
				Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
				Mockito.when(userRepository.delete((long)user.getId())).thenReturn(true);
			} else if (user.getId().equals(2)) {
				Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user));
				Mockito.when(userRepository.delete((long)user.getId())).thenReturn(true);
			} else if(user.getId().equals(3)) {
				Mockito.when(userRepository.findById(3L)).thenReturn(Optional.of(user));
				Mockito.when(userRepository.delete((long)user.getId())).thenReturn(true);
			}
			if(user.getName().getName().equals("taro")) {
				Mockito.when(userRepository.findByName("taro")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUsers("taro")).thenReturn(Collections.singletonList(user));
				Mockito.when(userRepository.findUserByNameOrEmail("taro")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUserByNameOrEmail("taro@taro.com")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findByEmail("taro@taro.com")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findByName("taro")).thenReturn(Optional.of(user));
				if(user.getId() < 0 || ObjectUtils.isEmpty(user.getName()) || ObjectUtils.isEmpty(user.getEmail()) || user.getDescription()  == null  || user.getCoverImage() == null || user.getProfileImage()  == null) {
					Mockito.doThrow(ConstraintViolationException.class);
				}
			} else if(user.getName().getName().equals("jiro")) {
				Mockito.when(userRepository.findByName("jiro")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUsers("jiro")).thenReturn(Collections.singletonList(user));
				Mockito.when(userRepository.findUserByNameOrEmail("jiro")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUserByNameOrEmail("jiro@jiro.com")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findByEmail("jiro@jiro.com")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findByName("jiro")).thenReturn(Optional.of(user));
				if(user.getId() < 0 || ObjectUtils.isEmpty(user.getName()) || ObjectUtils.isEmpty(user.getEmail()) || user.getDescription()  == null  || user.getCoverImage() == null || user.getProfileImage()  == null) {
					Mockito.doThrow(ConstraintViolationException.class);
				}
			} else if(user.getName().getName().equals("saburo")) {
				Mockito.when(userRepository.findByName("saburo")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUsers("saburo")).thenReturn(Collections.singletonList(user));
				Mockito.when(userRepository.findUserByNameOrEmail("saburo")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findUserByNameOrEmail("saburo@saburo.com")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findByEmail("saburo@saburo.com")).thenReturn(Optional.of(user));
				Mockito.when(userRepository.findByName("saburo")).thenReturn(Optional.of(user));
			}
		}

		Mockito.when(userRepository.create(Mockito.isA(User.class), Mockito.anyString())).thenReturn(4);
		Mockito.when(userRepository.update(Mockito.isA(User.class)))
				.thenAnswer(invocation -> {
					User userWithUpdateArgument = invocation.getArgument(0);
					for(User userInMockList : users) {
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
							return true;
						}
					}
					return false;
				});

		Mockito.when(uploader.uploadImage(Mockito.isA(MultipartFile.class)))
				.thenAnswer(invocation -> {
					MultipartFile image = invocation.getArgument(0);
					if(ObjectUtils.isEmpty(image)) {
						return null;
					}
					return "uploadedFileName";
				});

		userService = new UserServiceImpl(userRepository, validator, uploader);
		ReflectionTestUtils.setField(userService, "imagePath", "/images/");
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
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.findById(null);
		});
		Assertions.assertThrows(NotFoundException.class, () -> {
			userService.findById(100L);
		});

	}

	@Test
	void testFindUsers() {
		Collection<User> userFoundByName = userService.findUsers("taro");
		Assertions.assertEquals(1, userFoundByName.stream().findFirst().get().getId());
		Assertions.assertEquals("taro", userFoundByName.stream().findFirst().get().getName().getName());
		Assertions.assertEquals("taro@taro.com", userFoundByName.stream().findFirst().get().getEmail());
		Assertions.assertEquals("encryptedPassword",
				userFoundByName.stream().findFirst().get().getPassword().getPassword());
		Assertions.assertEquals(1, userFoundByName.stream().findFirst().get().getStatus());
		Assertions.assertEquals("", userFoundByName.stream().findFirst().get().getProfileImage());
		Assertions.assertEquals("", userFoundByName.stream().findFirst().get().getCoverImage());
		Collection<User> userNull = userService.findUsers(null);
		Assertions.assertEquals(3, userNull.size());
		Collection<User> userEmpty = userService.findUsers("");
		Assertions.assertEquals(3, userEmpty.size());
		Collection<User> userNotFound = userService.findUsers("test");
		Assertions.assertEquals(0, userNotFound.size());
	}

	@Test
	void testCreateUser() {
		User user = new User(new UserName("shiro"), "shiro@shiro.com", new Password("password"));
		Integer createdId = userService.create(user, "encryptedPassword");
		Assertions.assertEquals(4, createdId);
		Assertions.assertEquals(2, user.getStatus());
		Assertions.assertEquals("", user.getDescription());
		Assertions.assertEquals("", user.getProfileImage());
		Assertions.assertEquals("", user.getCoverImage());
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.create(user, "");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.create(user, null);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.create(null, "encryptedPassword");
		});
	}

	@Test
	void testUpdateUser() {

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


		User successUser = new User(1, new UserName("goro"), "goro@goro.com", "説明5",
				"/image/imagePath1", "/image/imagePath2");
		Optional<User> updatedSuccessUser = userService.update(successUser, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		Assertions.assertTrue(updatedSuccessUser.isPresent());
		Assertions.assertEquals("/images/" + "uploadedFileName", updatedSuccessUser.get().getProfileImage());
		Assertions.assertEquals("/images/" + "uploadedFileName", updatedSuccessUser.get().getCoverImage());

        User userWithNullProfileImageFile = new User(1, new UserName("goro"), "goro@goro.com", "説明5",
                "/image/imagePath1", "/image/imagePath2");
        Optional<User> updatedUserWithNullProfileImageFile = userService.update(userWithNullProfileImageFile, null, mockMultipartFileCoverImage);
        Assertions.assertTrue(updatedUserWithNullProfileImageFile.isPresent());
        Assertions.assertEquals("/images/" + "uploadedFileName", updatedUserWithNullProfileImageFile.get().getProfileImage());
        Assertions.assertEquals("/images/" + "uploadedFileName", updatedUserWithNullProfileImageFile.get().getCoverImage());

		User userWithNullCoverImageFile = new User(1, new UserName("goro"), "goro@goro.com", "説明5",
				"/image/imagePath1", "/image/imagePath2");
		Optional<User> updatedUserWithNullCoverImageFile = userService.update(userWithNullCoverImageFile, null, mockMultipartFileCoverImage);
		Assertions.assertTrue(updatedUserWithNullCoverImageFile.isPresent());
		Assertions.assertEquals("/images/" + "uploadedFileName", updatedUserWithNullProfileImageFile.get().getProfileImage());
		Assertions.assertEquals("/images/" + "uploadedFileName", updatedUserWithNullCoverImageFile.get().getCoverImage());

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.update(null, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		});

		User userWithPassword = new User(1, new UserName("goro"), "goro@goro.com", "説明5",
				"/image/imagePath1", "/image/imagePath2");
		userWithPassword.setPassword(new Password("password"));
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.update(userWithPassword, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		});

		User userWithStatus = new User(1, new UserName("goro"), "goro@goro.com", "説明5",
				"/image/imagePath1", "/image/imagePath2");
		userWithStatus.setStatus(2);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.update(userWithStatus, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		});

		User userWithNegativeId = new User(-1000, new UserName("goro"), "goro@goro.com", "説明5",
				"/image/imagePath1", "/image/imagePath2");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.update(userWithNegativeId, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		});

		User userWithNullId = new User(null, new UserName("goro"), "goro@goro.com", "説明5",
				"/image/imagePath1", "/image/imagePath2");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.update(userWithNullId, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		});

		User userWithNullUserName = new User(1, null, "goro@goro.com", "説明5",
				"/image/imagePath1", "/image/imagePath2");
		setValidation(userWithNullUserName);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userService.update(userWithNullUserName, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		});

		User userWithNullEmail = new User(1, new UserName("goro"), null, "説明5",
				"/image/imagePath1", "/image/imagePath2");
		setValidation(userWithNullEmail);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userService.update(userWithNullEmail, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		});

		User userWithEmptyEmail = new User(1, new UserName("goro"), "", "説明5",
				"/image/imagePath1", "/image/imagePath2");
		setValidation(userWithEmptyEmail);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			userService.update(userWithEmptyEmail, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		});

		User userWithNullProfileImage = new User(1, new UserName("goro"), "", "説明5",
				null, "/image/imagePath2");
		Optional<User> resultUserWithNullProfileImage = userService.update(userWithNullProfileImage, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		Assertions.assertTrue(resultUserWithNullProfileImage.isPresent());

		User userWithNullCoverImage = new User(1, new UserName("goro"), "", "説明5",
				"/image/imagePath1", null);
		Optional<User> resultWithNullCoverImage = userService.update(userWithNullCoverImage, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		Assertions.assertTrue(resultWithNullCoverImage.isPresent());

		User userWithNullDescription = new User(1, new UserName("goro"), "", null,
				"/image/imagePath1", "/image/imagePath2");
		Optional<User> resultUserWithNullDescription = userService.update(userWithNullDescription, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		Assertions.assertTrue(resultUserWithNullDescription.isPresent());

		User user1000 = new User(1000, new UserName("taro"), "taro@taro.com", "説明1000",
				"/image/imagePath1", "/image/imagePath2");
		Assertions.assertThrows(NotFoundException.class, () -> {
			userService.update(user1000, mockMultipartFileProfileImage, mockMultipartFileCoverImage);
		});
	}

	@Test
	void testDelete() {
		boolean isSuccess = userService.delete(1L);
		Assertions.assertTrue(isSuccess);
		Assertions.assertThrows(NotFoundException.class, () -> {
			userService.delete(100L);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.delete(-3L);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.delete(0L);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.delete(null);
		});
	}

	//
	@Test
	void testFindByUserNameOrEmail() {
		Assertions.assertThrows(NotFoundException.class, () -> {
			userService.findByUserNameOrEmail("test@test.com");
		});
		Optional<User> jiro = userService.findByUserNameOrEmail("jiro@jiro.com");
		Assertions.assertEquals(2, jiro.get().getId());
		Assertions.assertEquals("jiro@jiro.com", jiro.get().getEmail());
		Assertions.assertEquals("jiro", jiro.get().getName().getName());
		Assertions.assertEquals("encryptedPassword", jiro.get().getPassword().getPassword());
		Assertions.assertEquals(1, jiro.get().getStatus());
		Assertions.assertEquals("説明2", jiro.get().getDescription());

		Optional<User> saburo = userService.findByUserNameOrEmail("saburo@saburo.com");
		Assertions.assertEquals(3, saburo.get().getId());
		Assertions.assertEquals("saburo@saburo.com", saburo.get().getEmail());
		Assertions.assertEquals("saburo", saburo.get().getName().getName());
		Assertions.assertEquals("encryptedPassword", saburo.get().getPassword().getPassword());
		Assertions.assertEquals(1, saburo.get().getStatus());
		Assertions.assertEquals("説明3", saburo.get().getDescription());

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.findByUserNameOrEmail(null);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.findByUserNameOrEmail("");
		});
	}

	@Test
	void testFindByEmail() {
		Optional<User> user = userService.findByEmail("taro@taro.com");
		Assertions.assertEquals(1, user.get().getId());
		Assertions.assertEquals("taro@taro.com", user.get().getEmail());
		Assertions.assertEquals("taro", user.get().getName().getName());
		Assertions.assertEquals("encryptedPassword", user.get().getPassword().getPassword());
		Assertions.assertEquals(1, user.get().getStatus());
		Assertions.assertEquals("説明1", user.get().getDescription());

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.findByEmail(null);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.findByEmail("");
		});
		Assertions.assertThrows(NotFoundException.class, () -> {
			userService.findByEmail("test@test.com");
		});
	}

	@Test
	void testFindByName() {
		Optional<User> user = userService.findByName("taro");
		Assertions.assertEquals(1, user.get().getId());
		Assertions.assertEquals("taro@taro.com", user.get().getEmail());
		Assertions.assertEquals("taro", user.get().getName().getName());
		Assertions.assertEquals("encryptedPassword", user.get().getPassword().getPassword());
		Assertions.assertEquals(1, user.get().getStatus());
		Assertions.assertEquals("説明1", user.get().getDescription());

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.findByName(null);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.findByName("");
		});
		Assertions.assertThrows(NotFoundException.class, () -> {
			userService.findByEmail("test");
		});
	}

	void setValidation(User updateUserRequest) {
		if (updateUserRequest.getId() < 0 || ObjectUtils.isEmpty(updateUserRequest.getName()) || ObjectUtils.isEmpty(updateUserRequest.getEmail())
				|| updateUserRequest.getDescription() == null || updateUserRequest.getCoverImage() == null || updateUserRequest.getProfileImage() == null) {
			Mockito.when(validator.validate(updateUserRequest))
					.thenThrow(ConstraintViolationException.class);
		}
	}

}
