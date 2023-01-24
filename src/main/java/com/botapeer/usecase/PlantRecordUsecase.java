package com.botapeer.usecase;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.controller.payload.plantRecord.CreatePlantRecordRequest;
import com.botapeer.controller.payload.plantRecord.PlantRecordResponse;
import com.botapeer.domain.model.Label;
import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.service.FileUploadService;
import com.botapeer.domain.service.interfaces.ILabelService;
import com.botapeer.domain.service.interfaces.IPlantRecordService;
import com.botapeer.domain.service.interfaces.IUserService;
import com.botapeer.s3.FileUploadForm;
import com.botapeer.usecase.plantRecord.CreatePlantRecordRequestDto;
import com.botapeer.usecase.plantRecord.PlantRecordResponseDto;
import com.botapeer.util.ValidationUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PlantRecordUsecase implements IPlantRecordUsecase {

	private final IPlantRecordService plantRecordService;
	private final FileUploadService fileUploadService;
	private final IUserService userService;
	private final ILabelService labelService;
	private final ValidationUtils validation;

	Logger logger = LoggerFactory.getLogger(PlantRecordUsecase.class);

	@Value(value = "${imagePath}")
	private String imagePath;

	@Override
	public Optional<PlantRecordResponse> findById(String plantId) {
		try {
			int id = Integer.parseInt(plantId);
			Optional<PlantRecord> record = plantRecordService.findById(id);
			Optional<PlantRecordResponse> r = PlantRecordResponseDto.toResponse(record);
			return r;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return Optional.empty();
	}

	@Override
	public Optional<PlantRecordResponse> create(CreatePlantRecordRequest request, BindingResult result,
			Principal principal) {

		validation.validation(result);

		PlantRecord plantRecord = CreatePlantRecordRequestDto.toModel(request);

		plantRecord.setAlive(1);
		plantRecord.setStatus(1);

		String userName = principal.getName();
		Optional<User> user = userService.findByName(userName);

		plantRecord.setUserId(user.get().getId());

		Optional<PlantRecord> resultRecord = plantRecordService.create(plantRecord);
		Collection<Label> labels = labelService.findById(resultRecord.get().getId());

		resultRecord.get().setLabels(labels);

		return PlantRecordResponseDto.toResponse(resultRecord);
	}

	//	@Override
	//	public Collection<PlantRecordResponse> findPlantRecords(String name) {
	//		Collection<PlantRecordEntity> user = userService.findUsers(name);
	//		Collection<PlantRecordResponse> r = UserResponseDto.toResponse(user);
	//		return r;
	//	}

	//	@Override
	//	public Optional<PlantRecordResponse> update(
	//			Principal principal,
	//			CreatePlantRecordRequest request,
	//			MultipartFile profileImage,
	//			MultipartFile coverImage,
	//			BindingResult result) {
	//
	//		validation.validation(result);
	//
	//		String name = principal.getName();
	//		Optional<User> targetUser = userService.findByName(name);
	//
	//		if (request.getStatus() == null) {
	//			request.setStatus(targetUser.get().getStatus());
	//		}
	//
	//		User u = CreatePlantRecordRequestDto.toModel(request);
	//
	//		u.setId(targetUser.get().getId());
	//
	//		String coverImageName = uploadImage(coverImage);
	//		if (StringUtils.isEmpty(coverImageName)) {
	//			u.setCoverImage(targetUser.get().getCoverImage());
	//		} else {
	//			System.out.println("coverImageName: " + coverImageName);
	//			u.setCoverImage(imagePath + coverImageName);
	//		}
	//
	//		String profileImageName = uploadImage(profileImage);
	//		if (StringUtils.isEmpty(profileImageName)) {
	//			u.setProfileImage(targetUser.get().getProfileImage());
	//		} else {
	//			System.out.println("profileImageName: " + profileImageName);
	//			u.setProfileImage(imagePath + profileImageName);
	//		}
	//
	//		u.setPassword(targetUser.get().getPassword());
	//
	//		if (!userService.update(u)) {
	//			throw new Error();
	//		}
	//
	//		Optional<User> userModel = userService.findById((long) targetUser.get().getId());
	//
	//		Optional<PlantRecordResponse> r = UserResponseDto.toResponse(userModel);
	//		return r;
	//	}

	//	@Override
	//	public void delete(String userId) {
	//
	//	}

	//	@Override
	//	public boolean create(UserRequest user) {
	//
	//		return false;
	//	}

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
