package com.botapeer.usecase;

import java.util.Collection;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.constants.ResponseConstants;
import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.service.interfaces.IPlantRecordService;
import com.botapeer.domain.service.interfaces.IUserService;
import com.botapeer.exception.NotFoundException;
import com.botapeer.usecase.dto.user.UpdateUserRequestDto;
import com.botapeer.usecase.dto.user.UserResponseDto;
import com.botapeer.usecase.interfaces.IUserUsecase;
import com.botapeer.util.ImageUtils;

import lombok.RequiredArgsConstructor;
import model.CreateUserRequest;
import model.UpdateUserFormData;
import model.UserResponse;

@Component
@RequiredArgsConstructor
public class UserUsecase implements IUserUsecase {

	private final IUserService userService;

	private final IPlantRecordService plantRecordService;
	private final PasswordEncoder passwordEncoder;
	private final ImageUtils imageUtils;

	@Value(value = "${imagePath}")
	private String imagePath;

	Logger logger = LoggerFactory.getLogger(UserUsecase.class);

	@Override
	public Optional<UserResponse> findById(String userId) {
		try {
			int id = Integer.parseInt(userId);
			Optional<User> user = userService.findById((long) id);
			Optional<UserResponse> r = UserResponseDto.toResponse(user);
			return r;
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		}
		return Optional.empty();
	}

	@Override
	public Collection<UserResponse> findUsers(String name) {
		Collection<User> user = userService.findUsers(name);
		if (user.isEmpty()) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_USER_CODE);
		}
		Collection<UserResponse> r = UserResponseDto.toResponse(user);
		return r;
	}

	@Override
	public Optional<UserResponse> create(CreateUserRequest request) {

		User u = UpdateUserRequestDto.toModel(request);

		Password password = new Password(request.getPassword());

		String encryptedPassword = passwordEncoder.encode(password.getPassword());

		int userId = userService.create(u, encryptedPassword);
		Optional<User> user = userService.findById((long) userId);

		Optional<UserResponse> r = UserResponseDto.toResponse(user);
		return r;
	}

	@Override
	public Optional<UserResponse> update(
			UpdateUserFormData formData,
			MultipartFile profileImage,
			MultipartFile coverImage) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String name = auth.getName();
		Optional<User> targetUser = userService.findByName(name);

		User u = UpdateUserRequestDto.toModel(formData);

		u.setId(targetUser.get().getId());

		String coverImageName = imageUtils.uploadImage(coverImage);
		if (StringUtils.isEmpty(coverImageName)) {
			u.setCoverImage(targetUser.get().getCoverImage());
		} else {
			u.setCoverImage(imagePath + coverImageName);
		}

		String profileImageName = imageUtils.uploadImage(profileImage);
		if (StringUtils.isEmpty(profileImageName)) {
			u.setProfileImage(targetUser.get().getProfileImage());
		} else {
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

	//	@Override
	//	public Optional<model.User> updatePassword(Principal principal, UpdatePasswordRequest request,
	//			BindingResult result) {
	//
	//		//		validation.validation(result);
	//
	//		if (userService.updatePassword(request)) {
	//			throw new Error();
	//		}
	//
	//		String name = principal.getName();
	//		Optional<User> user = userService.findByName(name);
	//
	//		Optional<model.User> r = UserResponseDto.toResponse(user);
	//
	//		return r;
	//	}

	@Override
	public void delete(String userId) {
	}

	@Override
	public Optional<UserResponse> findByPlantRecordId(String plantRecordId) {
		try {
			Long id = Long.parseLong(plantRecordId);

			Optional<PlantRecord> plantRecord = plantRecordService.findById(id);
			if (ObjectUtils.isEmpty(plantRecord)) {
				throw new NotFoundException(ResponseConstants.NOTFOUND_PLANT_RECORD_CODE);
			}

			int userId = plantRecord.get().getUserId();

			Optional<User> user = userService.findById((long) userId);

			Optional<UserResponse> r = UserResponseDto.toResponse(user);

			return r;
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
