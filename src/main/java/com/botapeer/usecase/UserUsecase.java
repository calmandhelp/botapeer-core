package com.botapeer.usecase;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.controller.payload.user.UpdatePasswordRequest;
import com.botapeer.controller.payload.user.UpdateUserRequest;
import com.botapeer.controller.payload.user.UserResponse;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.service.FileUploadService;
import com.botapeer.domain.service.IUserService;
import com.botapeer.s3.FileUploadForm;
import com.botapeer.usecase.dto.user.UpdateUserRequestDto;
import com.botapeer.usecase.dto.user.UserResponseDto;
import com.botapeer.util.ValidationUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUsecase implements IUserUsecase {

	private final IUserService userService;
	private final FileUploadService fileUploadService;
	private final PasswordEncoder passwordEncoder;
	private final MessageSource messageSource;
	private final ValidationUtils validation;

	Logger logger = LoggerFactory.getLogger(UserUsecase.class);

	@Value(value = "${imagePath}")
	private String imagePath;

	@Override
	public Optional<UserResponse> findById(String userId) {
		try {
			int id = Integer.parseInt(userId);
			Optional<User> user = userService.findById((long) id);
			Optional<UserResponse> r = UserResponseDto.toResponse(user);
			return r;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return Optional.empty();
	}

	@Override
	public Collection<UserResponse> findUsers(String name) {
		Collection<User> user = userService.findUsers(name);
		Collection<UserResponse> r = UserResponseDto.toResponse(user);
		return r;
	}

	@Override
	public Optional<UserResponse> update(
			Principal principal,
			UpdateUserRequest request,
			MultipartFile profileImage,
			MultipartFile coverImage,
			BindingResult result) {

		validation.validation(result);

		String name = principal.getName();
		Optional<User> targetUser = userService.findByName(name);

		if (request.getStatus() == null) {
			request.setStatus(targetUser.get().getStatus());
		}

		User u = UpdateUserRequestDto.toModel(request);

		u.setId(targetUser.get().getId());

		String coverImageName = uploadImage(coverImage);
		if (StringUtils.isEmpty(coverImageName)) {
			u.setCoverImage(targetUser.get().getCoverImage());
		} else {
			System.out.println("coverImageName: " + coverImageName);
			u.setCoverImage(imagePath + coverImageName);
		}

		String profileImageName = uploadImage(profileImage);
		if (StringUtils.isEmpty(profileImageName)) {
			u.setProfileImage(targetUser.get().getProfileImage());
		} else {
			System.out.println("profileImageName: " + profileImageName);
			u.setProfileImage(imagePath + profileImageName);
		}

		u.setPassword(targetUser.get().getPassword());

		if (!userService.update(u)) {
			throw new Error();
		}

		Optional<User> userModel = userService.findById((long) targetUser.get().getId());

		Optional<UserResponse> r = UserResponseDto.toResponse(userModel);
		return r;
	}

	@Override
	public Optional<UserResponse> updatePassword(Principal principal, UpdatePasswordRequest request,
			BindingResult result) {

		validation.validation(result);

		if (userService.updatePassword(request)) {
			throw new Error();
		}

		String name = principal.getName();
		Optional<User> user = userService.findByName(name);

		Optional<UserResponse> r = UserResponseDto.toResponse(user);

		return r;
	}

	@Override
	public void delete(String userId) {
		// TODO 自動生成されたメソッド・スタブ

	}

	//	@Override
	//	public boolean create(UserRequest user) {
	//
	//		return false;
	//	}

	@Override
	public Optional<UserResponse> findByUserNameOrEmail(String usernameOrEmail) {
		// TODO 自動生成されたメソッド・スタブ
		return Optional.empty();
	}

	@Override
	public Optional<UserResponse> findByEmail(String email) {
		Optional<User> user = userService.findByEmail(email);
		Optional<UserResponse> r = UserResponseDto.toResponse(user);
		return r;
	}

	public String uploadImage(MultipartFile image) {
		if (!ObjectUtils.isEmpty(image)) {
			FileUploadForm fileUploadForm = new FileUploadForm();
			fileUploadForm.setMultipartFile(image);
			fileUploadForm.setCreateAt(LocalDateTime.now());
			try {
				System.out.println("image: " + image.getOriginalFilename());
				String fileName = fileUploadService.fileUpload(fileUploadForm, "botapeer.com/images");
				return fileName;
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return null;
	}
}
