package com.botapeer.usecase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.botapeer.exception.DifferentUserException;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.botapeer.util.NumberUtils;
import com.botapeer.util.StringUtil;
import com.botapeer.util.ValidateUtils;

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
	private final Validator validator;

	Logger logger = LoggerFactory.getLogger(UserUsecase.class);

	@Override
	public Optional<UserResponse> findById(String userId) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(userId, "userId is null or empty");
		validationMessage.ifPresent(msg -> errorMessages.put("userId_nullOrEmpty", msg));
		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		if (!NumberUtils.canString2Number(userId)) {
			throw new IllegalArgumentException("userId cannot be converted to a number");
		}

		Integer userIdInteger = Integer.parseInt(userId);
		validationMessage = ValidateUtils.validateZeroOrNegative(userIdInteger, "userId is zero or negative");
		validationMessage.ifPresent(msg -> errorMessages.put("userId_ZeroOrNegative", msg));
		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		Optional<User> savedUser = userService.findById((long) userIdInteger);
		if (savedUser.isEmpty()) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_USER_CODE);
		}

		Optional<UserResponse> r = UserResponseDto.toResponse(savedUser);
		return r;
	}

	@Override
	public Collection<UserResponse> findUsers(String name) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(name, "userId is null or empty");
		validationMessage.ifPresent(msg -> errorMessages.put("userId_nullOrEmpty", msg));
		if (!errorMessages.isEmpty()) {
			name = null;
		}

		Collection<User> user = userService.findUsers(name);
		Collection<UserResponse> r = UserResponseDto.toResponse(user);
		return r;
	}

	@Override
	public Optional<UserResponse> create(CreateUserRequest request) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(request, "createUserRequest is null or empty");
		validationMessage.ifPresent(msg -> errorMessages.put("createUserRequest_nullOrEmpty", msg));

		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		User u = UpdateUserRequestDto.toModel(request);
		u.setDescription("");
		u.setProfileImage("");
		u.setCoverImage("");

		Set<ConstraintViolation<User>> violations = validator.validate(u);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

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
			MultipartFile coverImage,
			String userId) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(userId, "userId is null or empty");
		if(validationMessage.isPresent()) {
			errorMessages.put("userId_nullOrEmpty", validationMessage.get());
		} else if(!NumberUtils.canString2Number(userId)) {
			errorMessages.put("userId_CannotConvert2Number", "userId cannot be converted to a number");
		}

		validationMessage = ValidateUtils.validateNull(formData, "formData is null");
		validationMessage.ifPresent(message -> errorMessages.put("formData_nullOrEmpty", message));

		if (validationMessage.isPresent()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		Set<ConstraintViolation<UpdateUserFormData>> violations = validator.validate(formData);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String name = auth.getName();
		Optional<User> targetUser = userService.findByName(name);

		if(targetUser.isEmpty()) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_PLANT_RECORD_CODE);
		}

		Integer userIdInteger = Integer.parseInt(userId);
		validationMessage = ValidateUtils.validateZeroOrNegative(userIdInteger, "userId is zero or negative");
		validationMessage.ifPresent(s -> errorMessages.put("userId_ZeroOrNegative", s));

		if (validationMessage.isPresent()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		if(!targetUser.get().getId().equals(userIdInteger)) {
			throw new DifferentUserException("Access is Denied");
		}

		User u = UpdateUserRequestDto.toModel(formData);

		u.setId(targetUser.get().getId());

		Optional<User> updatedUser = userService.update(u, profileImage, coverImage);
		if (updatedUser.isEmpty()) {
			logger.error("user could not be updated");
			throw new RuntimeException();
		}

		return UserResponseDto.toResponse(updatedUser);
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
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(plantRecordId, "plantRecordId is null or empty");

		if(validationMessage.isPresent()) {
			errorMessages.put("plantRecordId_nullOrEmpty", validationMessage.get());
		} else if(!NumberUtils.canString2Number(plantRecordId)) {
			errorMessages.put("userId_CannotConvert2Number", "userId cannot be converted to a number");
		}
		if (validationMessage.isPresent()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		Long plantRcordIdLong = Long.parseLong(plantRecordId);
		validationMessage = ValidateUtils.validateZeroOrNegative(plantRcordIdLong, "plantRcordId is zero or negative");
		validationMessage.ifPresent(s -> errorMessages.put("userId_ZeroOrNegative", s));

		if (validationMessage.isPresent()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		Optional<PlantRecord> plantRecord = plantRecordService.findById(plantRcordIdLong);
		if (ObjectUtils.isEmpty(plantRecord)) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_PLANT_RECORD_CODE);
		}

		int userId = plantRecord.get().getUserId();

		Optional<User> user = userService.findById((long) userId);

		Optional<UserResponse> r = UserResponseDto.toResponse(user);

		return r;
	}

}
