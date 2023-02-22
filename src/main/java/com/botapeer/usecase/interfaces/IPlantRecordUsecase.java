package com.botapeer.usecase.interfaces;

import java.util.Collection;
import java.util.Optional;

import model.CreatePlantRecordRequest;
import model.PlantRecordResponse;

public interface IPlantRecordUsecase {
	public Optional<PlantRecordResponse> findById(String plantRecordId);

	public Optional<PlantRecordResponse> create(CreatePlantRecordRequest request);

	public Collection<PlantRecordResponse> findByUserId(String userId);

	//	public Optional<PlantRecordResponse> update(Principal principal, CreatePlantRecordRequest user, MultipartFile coverImage,
	//			MultipartFile profileImage,
	//			BindingResult result);
	//
	//	public void delete(String userId);

	//	public boolean create(UserRequest user);

}
