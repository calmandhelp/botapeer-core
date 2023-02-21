package com.botapeer.usecase;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.botapeer.domain.model.place.Place;
import com.botapeer.domain.service.interfaces.IPlaceService;
import com.botapeer.usecase.dto.place.PlaceResponseDto;
import com.botapeer.usecase.interfaces.IPlaceUsecase;

import lombok.RequiredArgsConstructor;
import model.PlaceResponse;

@Component
@RequiredArgsConstructor
public class PlaceUsecase implements IPlaceUsecase {

	private final IPlaceService placeService;

	@Override
	public Collection<PlaceResponse> findAll() {
		Collection<Place> places = placeService.findAll();
		return PlaceResponseDto.toResponse(places);
	}

}
