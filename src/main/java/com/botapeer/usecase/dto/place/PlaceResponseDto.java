package com.botapeer.usecase.dto.place;

import java.util.ArrayList;
import java.util.Collection;

import com.botapeer.domain.model.place.Place;

import model.PlaceResponse;

public class PlaceResponseDto {

	public static Collection<PlaceResponse> toResponse(Collection<Place> models) {

		Collection<PlaceResponse> responses = new ArrayList<>();

		for (Place model : models) {
			PlaceResponse r = new PlaceResponse();
			r.setId(model.getId());
			r.setName(model.getName());
			responses.add(r);
		}

		return responses;
	}
}
