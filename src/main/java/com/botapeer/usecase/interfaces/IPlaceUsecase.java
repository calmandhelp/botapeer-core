package com.botapeer.usecase.interfaces;

import java.util.Collection;

import com.botapeer.domain.model.place.Place;

public interface IPlaceUsecase {
	public Collection<Place> findAll();
	//
	//	public Optional<PlantRecordResponse> update(Principal principal, CreatePlantRecordRequest user, MultipartFile coverImage,
	//			MultipartFile profileImage,
	//			BindingResult result);
	//
	//	public void delete(String userId);

	//	public boolean create(UserRequest user);

}
