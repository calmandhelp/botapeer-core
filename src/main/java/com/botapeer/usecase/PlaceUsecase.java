package com.botapeer.usecase;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.botapeer.domain.model.place.Place;
import com.botapeer.domain.service.interfaces.IPlaceService;
import com.botapeer.usecase.interfaces.IPlaceUsecase;
import com.botapeer.util.ValidationUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PlaceUsecase implements IPlaceUsecase {

	private final IPlaceService placeService;
	private final ValidationUtils validation;

	@Override
	public Collection<Place> findAll() {
		return placeService.findAll();
	}

}
