package com.botapeer.usecase.interfaces;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.validation.BindingResult;

import com.botapeer.controller.payload.plantRecord.CreatePlantRecordRequest;
import com.botapeer.controller.payload.plantRecord.PlantRecordResponse;

public interface IPlantRecordUsecase {
	public Optional<PlantRecordResponse> findById(String plantRecordId);

	public Optional<PlantRecordResponse> create(CreatePlantRecordRequest request, BindingResult result,
			Principal principal);

	public Collection<PlantRecordResponse> findByUserId(String userId);
	//
	//	public Optional<PlantRecordResponse> update(Principal principal, CreatePlantRecordRequest user, MultipartFile coverImage,
	//			MultipartFile profileImage,
	//			BindingResult result);
	//
	//	public void delete(String userId);

	//	public boolean create(UserRequest user);

}
