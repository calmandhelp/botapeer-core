package com.botapeer.domain.service.interfaces;

import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.place.Place;

public interface IPlaceService {
	Collection<Place> findAll();

	Optional<Place> findById(Long placeId);

	//	public boolean create(Label label);

	//	public Collection<PlantRecordEntity> findByUserId(int userId);
	//
	//	public Collection<PlantRecordEntity> findAll();
	//
	//	public boolean update(PlantRecordEntity plant);
	//
	//	public boolean delete(int plantId);
}
