package com.botapeer.domain.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.botapeer.domain.model.place.Place;
import com.botapeer.domain.repository.IPlaceRepository;
import com.botapeer.domain.service.interfaces.IPlaceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceSeviceImpl implements IPlaceService {

	private final IPlaceRepository placeRepository;

	@Override
	public Collection<Place> findAll() {
		return placeRepository.findAll();
	}

	@Override
	public Optional<Place> findById(Long placeId) {
		return placeRepository.findById(placeId);
	}

	//	private final ILabelRepository labelRepository;

	//	@Override
	//	public Collection<Label> findById(int plantRecordId) {
	//		return labelRepository.findById(plantRecordId);
	//	}

	//	@Override
	//	public boolean create(Label label) {
	//		return labelRepository.create(label);
	//	}

	//	@Override
	//	public Collection<PlantRecordEntity> findByUserId(int userId) {
	//		return this.plantRepository.findByUserId(userId);
	//	}
	//
	//	@Override
	//	public Collection<PlantRecordEntity> findAll() {
	//		return this.plantRepository.findAll();
	//	}
	//
	//	@Override
	//	public boolean update(PlantRecordEntity plant) {
	//		return this.plantRepository.update(plant);
	//	}
	//
	//	@Override
	//	public boolean delete(int plantId) {
	//		return this.plantRepository.delete(plantId);
	//	}

}
