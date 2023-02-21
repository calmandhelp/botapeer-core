package com.botapeer.usecase.interfaces;

import java.util.Collection;

import model.PlaceResponse;

public interface IPlaceUsecase {
	public Collection<PlaceResponse> findAll();
	//
	//	public Optional<PlantRecordResponse> update(Principal principal, CreatePlantRecordRequest user, MultipartFile coverImage,
	//			MultipartFile profileImage,
	//			BindingResult result);
	//
	//	public void delete(String userId);

	//	public boolean create(UserRequest user);

}
