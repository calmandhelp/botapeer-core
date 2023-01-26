package com.botapeer.controller.payload.plantRecord;

import java.time.LocalDate;
import java.util.Collection;

import com.botapeer.controller.payload.post.PostResponse;
import com.botapeer.domain.model.place.Place;

import lombok.Data;

@Data
public class PlantRecordResponse {
	private Integer id;

	private String title;

	private Integer alive;

	private String endDate;

	private LocalDate createdAt;

	private LocalDate updatedAt;

	private Place place;

	private Collection<PostResponse> posts;

}
