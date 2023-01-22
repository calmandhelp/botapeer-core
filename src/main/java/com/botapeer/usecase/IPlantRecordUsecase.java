package com.botapeer.usecase;

import java.util.Optional;

import com.botapeer.controller.payload.plantRecord.PlantRecordResponse;

public interface IPlantRecordUsecase {
	public Optional<PlantRecordResponse> findById(String userId);

	//	public Collection<PlantRecordResponse> findPlantRecords(String name);
	//
	//	public Optional<PlantRecordResponse> update(Principal principal, UpdateUserRequest user, MultipartFile coverImage,
	//			MultipartFile profileImage,
	//			BindingResult result);
	//
	//	public void delete(String userId);

	//	public boolean create(UserRequest user);

}
