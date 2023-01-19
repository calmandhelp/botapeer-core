package com.botapeer.usecase;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.constants.ResponseConstants;
import com.botapeer.controller.exception.validation.ErrorMessages;
import com.botapeer.controller.exception.validation.ValidationException;
import com.botapeer.domain.entity.User;
import com.botapeer.domain.service.FileUploadService;
import com.botapeer.domain.service.IUserService;
import com.botapeer.payload.user.UpdatePasswordRequest;
import com.botapeer.s3.FileUploadForm;
import com.botapeer.util.ValidationUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUsecase implements IUserUsecase {

	private final IUserService userService;
	private final FileUploadService fileUploadService;
	private final PasswordEncoder passwordEncoder;
	private final MessageSource messageSource;
	private final ValidationUtil validation;

	@Value(value = "${imagePath}")
	private String imagePath;

	@Override
	public Optional<User> findById(Long userId) {
		// TODO 自動生成されたメソッド・スタブ
		return Optional.empty();
	}

	@Override
	public Collection<User> findUsers() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Optional<User> update(
			Principal principal,
			User user,
			MultipartFile profileImage,
			MultipartFile coverImage,
			BindingResult result) {

		String name = principal.getName();
		Optional<User> targetUser = userService.findByName(name);
		user.setId(targetUser.get().getId());

		if (result.hasErrors()) {
			List<HashMap<String, String>> list = new ArrayList<>();
			for (int i = 0; i < result.getErrorCount(); i++) {
				HashMap<String, String> errorsMap = new HashMap<>();
				errorsMap.put(ResponseConstants.ERRORS_CODE_KEY, result.getAllErrors().get(i).getCode());
				errorsMap.put(ResponseConstants.ERRORS_MESSAGE_KEY, result.getAllErrors().get(i).getDefaultMessage());
				list.add(errorsMap);
			}
			ErrorMessages errorMessages = new ErrorMessages();
			errorMessages.setMessages(list);
			throw new ValidationException(errorMessages);
		}

		String coverImageName = uploadImage(user, coverImage);
		if (StringUtils.isEmpty(coverImageName)) {
			user.setCoverImage(targetUser.get().getCoverImage());
		} else {
			System.out.println("coverImageName: " + coverImageName);
			user.setCoverImage(imagePath + coverImageName);
		}

		String profileImageName = uploadImage(user, profileImage);
		if (StringUtils.isEmpty(profileImageName)) {
			user.setProfileImage(targetUser.get().getProfileImage());
		} else {
			System.out.println("profileImageName: " + profileImageName);
			user.setProfileImage(imagePath + profileImageName);
		}

		if (!StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		} else {
			user.setPassword(targetUser.get().getPassword());
		}

		if (!userService.update(user)) {
			throw new Error();
		}
		Optional<User> u = userService.findById((long) user.getId());
		u.get().setPassword(null);
		return u;
	}

	@Override
	public Optional<User> updatePassword(UpdatePasswordRequest request, BindingResult result) {

		validation.validation(result);

		return Optional.empty();
	}

	@Override
	public boolean delete(Long userId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean create(User user) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Optional<User> findByUserNameOrEmail(String usernameOrEmail) {
		// TODO 自動生成されたメソッド・スタブ
		return Optional.empty();
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO 自動生成されたメソッド・スタブ
		return Optional.empty();
	}

	public String uploadImage(User user, MultipartFile image) {
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
