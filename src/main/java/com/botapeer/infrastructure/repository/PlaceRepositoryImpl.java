package com.botapeer.infrastructure.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.botapeer.domain.model.place.Place;
import com.botapeer.domain.repository.IPlaceRepository;
import com.botapeer.infrastructure.mapper.PlaceMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PlaceRepositoryImpl implements IPlaceRepository {

	private final PlaceMapper placeMapper;

	@Override
	public Collection<Place> findAll() {
		return placeMapper.findAll();
	}

	@Override
	public Optional<Place> findById(Long placeId) {
		return placeMapper.findById(placeId);
	}

	//	@Override
	//	public Collection<Label> findById(int id) {
	//		return labelMapper.findById(id);
	//	}

	//	@Override
	//	public boolean create(Label label) {
	//		return labelMapper.create(label);
	//	}

}
